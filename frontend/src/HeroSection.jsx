import { motion } from "framer-motion";
import { Search, MessageSquare, Sparkles, ArrowRight } from "lucide-react";

function HeroSection({ onOpenChat, t }) {
  return (
    <header className="hero">
      <div className="hero-content">
        <motion.div 
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.5 }}
          className="hero-badge"
        >
          <Sparkles size={14} />
          <span>{t.heroBadge || "Empowering Citizens with Information"}</span>
        </motion.div>

        <motion.h1
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.5, delay: 0.1 }}
        >
          {t.heroTitle.split(" ").slice(0, -1).join(" ")} <span>{t.heroTitle.split(" ").pop()}</span>
        </motion.h1>

        <motion.p
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.5, delay: 0.2 }}
        >
          {t.heroSub || "Find and apply for government schemes tailored to your profile. Simplified, accessible, and transparent for every citizen."}
        </motion.p>

        <motion.div 
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.5, delay: 0.3 }}
          className="hero-buttons"
        >
          <button 
            className="btn-hero btn-hero-primary" 
            onClick={() => {
              document.getElementById("schemes-section")?.scrollIntoView({ behavior: "smooth" });
            }}
          >
            <Search size={18} />
            {t.browseSchemes || t.findSchemes}
            <ArrowRight size={16} />
          </button>
          <button className="btn-hero btn-hero-outline" onClick={onOpenChat}>
            <MessageSquare size={18} />
            {t.askChatbot}
          </button>
        </motion.div>
      </div>
    </header>
  );
}

export default HeroSection;
