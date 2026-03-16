import { useState, useRef, useEffect, useCallback } from "react";
import { schemeService } from "./api";

const STEPS = {
  START: "START",
  ASK_STATE: "ASK_STATE",
  ASK_GENDER: "ASK_GENDER",
  ASK_CASTE: "ASK_CASTE",
  ASK_INCOME: "ASK_INCOME",
  ASK_CATEGORY: "ASK_CATEGORY",
  COMPLETED: "COMPLETED",
};

function ChatWidget({ isOpen, onClose, onToggle, t }) {
  const [messages, setMessages] = useState([
    {
      id: 1,
      role: "bot",
      text: t.chatWelcome,
    },
  ]);
  const [input, setInput] = useState("");
  const [flowStep, setFlowStep] = useState(STEPS.START);
  const [userData, setUserData] = useState({
    state: "All",
    gender: "All",
    caste: "All",
    income: null,
    category: "All",
  });

  const messagesEndRef = useRef(null);

  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  };

  useEffect(() => {
    if (isOpen) scrollToBottom();
  }, [messages, isOpen]);

  // Restart chat if language changes to show translated welcome message (simple version)
  useEffect(() => {
    setMessages([{ id: Date.now(), role: "bot", text: t.chatWelcome }]);
    setFlowStep(STEPS.START);
  }, [t.chatWelcome]);

  const addBotMessage = (text) => {
    setMessages((prev) => [...prev, { id: Date.now(), role: "bot", text }]);
  };

  const handleGuidedSearch = async (val) => {
    const low = val.toLowerCase();

    // Reset logic
    if (low === "reset" || low === "restart" || low.includes("start over")) {
      setFlowStep(STEPS.START);
      addBotMessage("Starting over! Type 'yes' to begin the guided search.");
      return;
    }

    switch (flowStep) {
      case STEPS.START:
        if (low.includes("yes") || low.includes("start") || low.includes("guided") || low.includes("हाँ") || low.includes("అవును")) {
          setFlowStep(STEPS.ASK_STATE);
          addBotMessage("Great! 📍 First, which **State** do you live in? (e.g., 'Rajasthan', 'Telangana', or type 'All')");
        } else {
            return null; 
        }
        break;

      case STEPS.ASK_STATE:
        setUserData(prev => ({ ...prev, state: val }));
        setFlowStep(STEPS.ASK_GENDER);
        addBotMessage(`Noted! You are from ${val}. 🚻 Next, what is your **Gender**? (Male/Female/All)`);
        break;

      case STEPS.ASK_GENDER:
        setUserData(prev => ({ ...prev, gender: val }));
        setFlowStep(STEPS.ASK_CASTE);
        addBotMessage("Got it. 🏷️ What is your **Caste** category? (General/OBC/SC/ST/EWS/All)");
        break;

      case STEPS.ASK_CASTE:
        setUserData(prev => ({ ...prev, caste: val }));
        setFlowStep(STEPS.ASK_INCOME);
        addBotMessage("💰 What is your **Annual Household Income**? (e.g., 500000, or type '0' if no limit)");
        break;

      case STEPS.ASK_INCOME:
        const income = parseFloat(val) || 0;
        setUserData(prev => ({ ...prev, income }));
        setFlowStep(STEPS.ASK_CATEGORY);
        addBotMessage("📂 Finally, which **Category** are you interested in? (e.g., Education, Agriculture, Health, or type 'All')");
        break;

      case STEPS.ASK_CATEGORY:
        const finalCategory = val;
        setUserData(prev => ({ ...prev, category: finalCategory }));
        setFlowStep(STEPS.COMPLETED);
        
        addBotMessage("🔄 Finding schemes based on your profile...");
        try {
            // Normalize inputs to match Enums and logic in SchemeRepository/Dashboard
            const normalize = (val) => {
                if (!val || val.toLowerCase() === "all" || val.toLowerCase() === "any") return null;
                return val.trim().toUpperCase().replace(/\s+/g, '_');
            };

            const params = { size: 5, sort: "id,desc" };
            
            const categoryEnum = normalize(finalCategory);
            const stateVal = (userData.state && userData.state.toLowerCase() !== "all") ? userData.state : null;
            const genderEnum = normalize(userData.gender);
            const casteEnum = normalize(userData.caste);

            if (categoryEnum) params.category = categoryEnum;
            if (stateVal) params.state = stateVal;
            if (genderEnum && genderEnum !== "ALL") params.gender = genderEnum;
            console.log("Chatbot search params:", params);
            // alert("DEBUG: Starting chatbot search for " + (params.category || "any"));

            const response = await schemeService.getSchemes(params);
            
            // Robustly handle both ApiResponse and direct Page responses
            const pageObj = response?.data || response;
            const data = pageObj?.content || [];
            const total = pageObj?.totalElements || 0;

            if (data && data.length > 0) {
                const names = data.slice(0, 3).map(s => `• **${s.name}**`).join("\n");
                addBotMessage(`🎉 I found **${total}** schemes for you!\n\n${names}\n\nCheck the Dashboard for full details. Type 'reset' to start a new search!`);
            } else {
                addBotMessage("😔 I couldn't find any specific schemes matching your exact profile. Try adjusting your filters or checking the 'All' schemes on the Dashboard! Type 'reset' to try again.");
            }
        } catch (e) {
            console.error("Chatbot Search Error:", e);
            // Handle various error formats (Axios error, Rejected Promise object, etc.)
            const errorDetails = e.response?.data?.message || e.message || (typeof e === 'object' ? (e.message || e.error || JSON.stringify(e)) : e);
            addBotMessage(`⚠️ I had trouble fetching the results.\n\n**Error:** ${errorDetails}\n\n**Note:** If this persists, please try a **Hard Refresh (Ctrl + F5)** in your browser.`);
        }
        break;

      default:
        return null;
    }
    return true;
  };

  const getKeywordResponse = (input) => {
    const low = input.toLowerCase();
    
    if (low === "help" || low === "list" || low.includes("what can you do")) {
        return "I can provide a **Guided Search** (type 'yes') or answer quick questions about categories like **Education**, **Agriculture**, **Health**, etc.";
    }

    if (low.includes("education") || low.includes("student") || low.includes("scholarship"))
        return "🎓 For Education, we have schemes like PM Scholarship and Rajasthan Anuprati. Type 'yes' for a personalized check!";
    if (low.includes("farmer") || low.includes("agriculture") || low.includes("kisan"))
        return "🚜 For Agriculture, PM-Kisan is a top scheme. Type 'yes' to see if you qualify for others!";
    if (low.includes("health") || low.includes("hospital") || low.includes("medical"))
        return "🏥 Ayushman Bharat provides up to 5 Lakh health cover. Type 'yes' to find more Health schemes!";

    return "📋 Type 'yes' to start a guided search for schemes you qualify for, or ask me about a category!";
  };

  const handleSend = async () => {
    const trimmed = input.trim();
    if (!trimmed) return;

    const userMsg = { id: Date.now(), role: "user", text: trimmed };
    setMessages(prev => [...prev, userMsg]);
    setInput("");

    const wasGuided = await handleGuidedSearch(trimmed);
    
    if (!wasGuided) {
        const botResponse = getKeywordResponse(trimmed);
        addBotMessage(botResponse);
    }
  };

  const handleKeyDown = (e) => {
    if (e.key === "Enter" && !e.shiftKey) {
      e.preventDefault();
      handleSend();
    }
  };

  return (
    <div className={`chat-widget-wrapper ${isOpen ? "open" : ""}`}>
      <button className="chat-floating-btn" onClick={onToggle} title={t.chatAssistant}>
        {isOpen ? "✕" : "🤖"}
      </button>

      <div className="chat-window">
        <div className="chat-window-header">
          <div className="header-info">
            <span className="dot"></span>
            <h3>{t.chatAssistant}</h3>
          </div>
          <button className="chat-close-header" onClick={onClose} title="Collapse">
            ✕
          </button>
        </div>

        <div className="chat-window-messages">
          {messages.map(m => (
            <div key={m.id} className={`chat-bubble ${m.role}`} style={{ whiteSpace: "pre-wrap" }}>
              {m.text}
            </div>
          ))}
          <div ref={messagesEndRef} />
        </div>

        <div className="chat-window-input">
          <input
            placeholder={flowStep === STEPS.START ? t.typeMessage : "Your answer..."}
            value={input}
            onChange={e => setInput(e.target.value)}
            onKeyDown={handleKeyDown}
          />
          <button onClick={handleSend} className="send-btn">
            {t.send}
          </button>
        </div>
      </div>
    </div>
  );
}

export default ChatWidget;
