import { useNavigate } from "react-router-dom";

function HeroSection({ onOpenChat, t }) {
  const navigate = useNavigate();

  return (
    <div className="hero">
      <div className="hero-content">
        <h1>{t.heroTitle}</h1>
        {t.heroSub && <p>{t.heroSub}</p>}
        <div className="hero-buttons">
          <button className="btn-outline" onClick={() => {
            document.getElementById("schemes-section")?.scrollIntoView({ behavior: "smooth" });
          }}>
            🔍 {t.browseSchemes}
          </button>
        </div>
      </div>
    </div>
  );
}

export default HeroSection;
