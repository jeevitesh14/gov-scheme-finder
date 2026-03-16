import { NavLink, useNavigate } from "react-router-dom";

const LANGUAGES = ["English", "Hindi", "Telugu"];

function Navbar({ user, onLogout, language, onLanguageChange, t }) {
  const navigate = useNavigate();

  return (
    <nav className="navbar">
      <NavLink to="/" className="navbar-logo">
        <span className="logo-icon">✔</span>
        {t.dashboard === "Dashboard" ? "Scheme Finder" : t.heroTitle.split(" ")[0] + " " + t.heroTitle.split(" ")[1]} 
      </NavLink>

      <ul className="navbar-links">
        <li><NavLink to="/" end>{t.dashboard}</NavLink></li>
        <li><NavLink to="/bookmarks">{t.bookmarks}</NavLink></li>
      </ul>

      <div className="navbar-right">
        <select 
          className="lang-select" 
          value={language} 
          onChange={(e) => onLanguageChange(e.target.value)}
        >
          {LANGUAGES.map(l => <option key={l} value={l}>{l}</option>)}
        </select>

        {user ? (
          <div className="nav-user">
            <div className="user-info">
              <span className="user-email">{user.email}</span>
              <span className="user-role-badge">{user.role}</span>
            </div>
            <button className="nav-auth-btn" onClick={onLogout}>
              {t.logout}
            </button>
          </div>
        ) : (
          <>
            <button className="nav-auth-btn" onClick={() => navigate("/login")}>🔑 {t.login}</button>
            <button className="nav-auth-btn outline" onClick={() => navigate("/register")}>📝 {t.register}</button>
          </>
        )}

        <button className="icon-btn" title="Settings">⚙️</button>
      </div>
    </nav>
  );
}

export default Navbar;
