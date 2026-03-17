import { useState, useRef, useEffect, useCallback } from "react";
import { 
  Send, 
  Bot, 
  X, 
  MessageSquare, 
  RotateCcw, 
  Sparkles,
  Search,
  ChevronDown
} from "lucide-react";
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

  // Restart chat if language changes to show translated welcome message
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
            const validateEnum = (val, allowed) => {
                if (!val) return null;
                const normalized = val.trim().toUpperCase().replace(/\s+/g, '_');
                if (allowed.includes(normalized)) return normalized;
                if (normalized === "ALL" || normalized === "ANY") return null;
                return null; // Ignore invalid values instead of causing 400
            };

            const params = { size: 5, sort: "id,desc" };
            
            const categoryEnum = validateEnum(finalCategory, ["EDUCATION", "HEALTH", "AGRICULTURE", "HOUSING", "FINANCE", "FOOD", "SOCIAL_WELFARE", "ENTREPRENEURSHIP", "OTHER"]);
            const stateVal = (userData.state && userData.state.toLowerCase() !== "all") ? userData.state : null;
            const genderEnum = validateEnum(userData.gender, ["MALE", "FEMALE", "ALL"]);
            const casteEnum = validateEnum(userData.caste, ["GENERAL", "OBC", "SC", "ST", "EWS", "ALL"]);

            if (categoryEnum) params.category = categoryEnum;
            if (stateVal) params.state = stateVal;
            if (genderEnum && genderEnum !== "ALL") params.gender = genderEnum;
            if (casteEnum && casteEnum !== "ALL") params.caste = casteEnum;
            if (userData.income > 0) params.income = userData.income;

            console.log("Fetching schemes with params:", params);
            const response = await schemeService.getSchemes(params);
            const pageObj = response?.data || response;
            const data = pageObj?.content || [];
            const total = pageObj?.totalElements || 0;

            if (data && data.length > 0) {
                const names = data.slice(0, 3).map(s => `• **${s.name}**`).join("\n");
                addBotMessage(`🎉 I found **${total}** schemes for you!\n\n${names}\n\nCheck the Dashboard for full details. Type 'reset' to start a new search!`);
            } else {
                addBotMessage("😔 I couldn't find any specific schemes matching your exact profile. Type 'reset' to try again.");
            }
        } catch (e) {
            console.error("Chat fetch error:", e);
            const errorDetails = e.response?.data?.message || e.message;
            addBotMessage(`⚠️ I had trouble fetching results. Type 'reset' to try again.`);
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
        return "🏥 Ayushman Bharat provides cover. Type 'yes' to find more Health schemes!";

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
        {isOpen ? <X size={32} /> : <MessageSquare size={32} />}
      </button>

      <div className="chat-window">
        <div className="chat-window-header">
          <div className="header-info">
            <div className="bot-avatar">
              <Sparkles size={20} />
            </div>
            <div className="header-info-text">
              <h3>{t.chatAssistant}</h3>
              <span className="status">AI Online</span>
            </div>
          </div>
          <button className="chat-close-header" onClick={onClose} title="Collapse">
            <ChevronDown size={20} />
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
          <div className="chat-input-container">
            <input
              placeholder={flowStep === STEPS.START ? t.typeMessage : "Your answer..."}
              value={input}
              onChange={e => setInput(e.target.value)}
              onKeyDown={handleKeyDown}
            />
          </div>
          <button onClick={handleSend} className="send-btn" disabled={!input.trim()}>
            <Send size={20} />
          </button>
        </div>
      </div>
    </div>
  );
}

export default ChatWidget;
