import { motion } from "framer-motion";
import { Search, MessageSquare, Sparkles, ArrowRight } from "lucide-react";
import { useTranslation } from "react-i18next";

function HeroSection({ onOpenChat, searchQuery, onSearchChange }) {
  const { t } = useTranslation();
  return (
    <header className="hero">
      <div className="hero-content">
        <motion.h1
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.5 }}
        >
          {t('heroTitle', "Government Scheme Eligibility").split(" ").slice(0, -1).join(" ")} <span>{t('heroTitle', "Finder").split(" ").pop()}</span>
        </motion.h1>

        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.5, delay: 0.1 }}
          className="hero-badge"
          style={{ marginBottom: '2.5rem' }}
        >
          <Sparkles size={14} />
          <span>{t('heroSub', "Empowering Citizens with Information")}</span>
        </motion.div>

        <motion.div 
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.5, delay: 0.2 }}
          className="hero-actions"
        >
          <button 
            className="btn-hero btn-hero-primary" 
            onClick={() => {
              document.getElementById("schemes-section")?.scrollIntoView({ behavior: "smooth" });
            }}
          >
            {t('browseSchemes', "View Schemes")}
            <ArrowRight size={16} />
          </button>

          <div className="hero-search-wrapper">
            <Search size={18} className="search-icon" />
            <input 
              type="text" 
              placeholder={t('searchPlaceholder', "Search schemes...")}
              value={searchQuery}
              onChange={(e) => onSearchChange(e.target.value)}
              className="hero-search-input"
            />
          </div>
        </motion.div>
      </div>
    </header>
  );
}

export default HeroSection;
