import { NavLink, useNavigate } from "react-router-dom";
import { 
  LayoutDashboard, 
  Bookmark, 
  LogOut, 
  User, 
  Languages, 
  Settings, 
  ShieldCheck,
  LogIn,
  UserPlus,
  Search
} from "lucide-react";

const LANGUAGES = ["English", "Hindi", "Telugu"];

function Navbar({ user, onLogout, language, onLanguageChange, t }) {
  const navigate = useNavigate();

  return (
    <nav className="navbar">
      <NavLink to="/" className="navbar-logo">
        <div className="logo-icon">
          <ShieldCheck size={20} fill="currentColor" fillOpacity={0.2} />
        </div>
        <span>
          {t.dashboard === "Dashboard" ? "SchemeFinder" : t.heroTitle.split(" ")[0] + t.heroTitle.split(" ")[1]} 
        </span>
      </NavLink>

      <ul className="navbar-links">
        <li>
          <NavLink to="/" end>
            <LayoutDashboard size={18} />
            <span>{t.dashboard}</span>
          </NavLink>
        </li>
        <li>
          <NavLink to="/bookmarks">
            <Bookmark size={18} />
            <span>{t.bookmarks}</span>
          </NavLink>
        </li>
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
        <div className="lang-select-wrapper">
          <Languages size={14} className="lang-select-icon" />
          <select 
            className="lang-select" 
            value={language} 
            onChange={(e) => onLanguageChange(e.target.value)}
          >
            {LANGUAGES.map(l => <option key={l} value={l}>{l}</option>)}
          </select>
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
            <button className="nav-logout-btn" onClick={onLogout} title={t.logout}>
              <LogOut size={18} />
            </button>
          </div>
        ) : (
          <div className="nav-auth-group" style={{ display: 'flex', gap: '0.75rem' }}>
            <button className="nav-auth-btn outline" onClick={() => navigate("/login")}>
              <LogIn size={16} />
              <span>{t.login}</span>
            </button>
            <button className="nav-auth-btn" onClick={() => navigate("/register")}>
              <UserPlus size={16} />
              <span>{t.register}</span>
            </button>
          </div>
        )}

        {/* <button className="icon-btn" title="Settings"><Settings size={20} /></button> */}
      </div>
    </nav>
  );
}

export default Navbar;
