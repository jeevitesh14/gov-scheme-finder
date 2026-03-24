import { useState, useRef, useEffect } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { motion, AnimatePresence } from "framer-motion";
import { 
  LayoutDashboard, 
  Bookmark, 
  LogOut, 
  User, 
  Languages, 
  ShieldCheck,
  LogIn,
  UserPlus,
  Clock,
  ChevronDown,
  FileText // Added FileText import as suggested by the instruction's snippet
} from "lucide-react";
import { useTranslation } from "react-i18next";
import ThemeToggle from "./ThemeToggle";

const LANGUAGES = [
  { code: "en", label: "English", native: "English" },
  { code: "hi", label: "Hindi", native: "हिन्दी" },
  { code: "te", label: "Telugu", native: "తెలుగు" }
];

function Navbar({ user, onLogout }) {
  const { t, i18n } = useTranslation();
  const navigate = useNavigate();
  const [isLangOpen, setIsLangOpen] = useState(false);
  const dropdownRef = useRef(null);

  // Close dropdown on click outside
  useEffect(() => {
    const handleClickOutside = (event) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setIsLangOpen(false);
      }
    };
    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, []);

  const currentLanguageCode = i18n.language || "English";
  const currentLang = LANGUAGES.find(l => l.code === currentLanguageCode) || LANGUAGES[0];

  const handleLanguageChange = (langCode) => {
    i18n.changeLanguage(langCode);
    setIsLangOpen(false);
  };

  return (
    <nav className="navbar">
      <NavLink to="/" className="navbar-logo">
        <div className="logo-icon">
          <ShieldCheck size={20} fill="currentColor" fillOpacity={0.2} />
        </div>
        <span>
          {t('dashboard') === "Dashboard" ? "SchemeFinder" : t('heroTitle').split(" ")[0] + t('heroTitle').split(" ")[1]} 
        </span>
      </NavLink>

      <ul className="navbar-links">
        <li>
          <NavLink to="/" end>
            <LayoutDashboard size={18} />
            <span>{t('dashboard')}</span>
          </NavLink>
        </li>
        {user && (
          <>
            <li>
              <NavLink to="/applications">
                <Clock size={18} />
                <span>{t('applications')}</span>
              </NavLink>
            </li>
            <li>
              <NavLink to="/profile">
                <User size={18} />
                <span>{t('profile')}</span>
              </NavLink>
            </li>
          </>
        )}
        {user?.role === "ADMIN" && (
          <li>
            <NavLink to="/admin">
              <ShieldCheck size={18} />
              <span>Admin</span>
            </NavLink>
          </li>
        )}
      </ul>

      <div className="navbar-right">
        <ThemeToggle />
        {/* Custom Language Dropdown */}
        <div className="lang-dropdown-container" ref={dropdownRef}>
          <button 
            className={`lang-dropdown-toggle ${isLangOpen ? 'active' : ''}`}
            onClick={() => setIsLangOpen(!isLangOpen)}
            aria-haspopup="listbox"
            aria-expanded={isLangOpen}
            type="button"
          >
            <Languages size={18} />
            <span className="current-lang-label">{currentLang.native}</span>
            <motion.div
              animate={{ rotate: isLangOpen ? 180 : 0 }}
              transition={{ duration: 0.2 }}
              style={{ display: "flex", alignItems: "center" }}
            >
              <ChevronDown size={14} />
            </motion.div>
          </button>

          <AnimatePresence>
            {isLangOpen && (
              <motion.ul 
                className="lang-dropdown-menu"
                initial={{ opacity: 0, y: 10, scale: 0.95 }}
                animate={{ opacity: 1, y: 0, scale: 1 }}
                exit={{ opacity: 0, y: 5, scale: 0.95 }}
                transition={{ duration: 0.2, ease: "easeOut" }}
                role="listbox"
              >
                {LANGUAGES.map((l) => (
                  <li key={l.code}>
                    <button 
                      className={`lang-option ${currentLanguageCode === l.code ? 'selected' : ''}`}
                      onClick={() => handleLanguageChange(l.code)}
                      role="option"
                      aria-selected={currentLanguageCode === l.code}
                      type="button"
                    >
                      <div className="lang-option-content">
                        <span className="lang-native">{l.native}</span>
                        <span className="lang-label-sub">{l.label}</span>
                      </div>
                      {currentLanguageCode === l.code && (
                        <motion.div 
                          layoutId="active-indicator"
                          className="active-indicator-dot"
                        />
                      )}
                    </button>
                  </li>
                ))}
              </motion.ul>
            )}
          </AnimatePresence>
        </div>

        {user ? (
          <div className="nav-user">
            <div className="user-avatar">
              {user.email.charAt(0).toUpperCase()}
            </div>
            <div className="user-info">
              <span className="user-email">{user.email.split('@')[0]}</span>
              <span className="user-role-badge">{user.role}</span>
            </div>
            <button className="nav-logout-btn" onClick={onLogout} title={t('logout')}>
              <LogOut size={18} />
            </button>
          </div>
        ) : (
          <div className="nav-auth-group" style={{ display: 'flex', gap: '0.75rem' }}>
            <button className="nav-auth-btn outline" onClick={() => navigate("/login")}>
              <LogIn size={16} />
              <span>{t('login')}</span>
            </button>
            <button className="nav-auth-btn" onClick={() => navigate("/register")}>
              <UserPlus size={16} />
              <span>{t('register')}</span>
            </button>
          </div>
        )}
      </div>
    </nav>
  );
}

export default Navbar;
