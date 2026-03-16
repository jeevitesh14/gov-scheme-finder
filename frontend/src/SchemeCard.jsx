function SchemeCard({ scheme, bookmarked, onBookmark, onClick, t }) {
  return (
    <div className="scheme-card" onClick={onClick}>
      <div className="card-top">
        <h3 className="scheme-name">{scheme.name}</h3>
        <button
          className={`bookmark-btn ${bookmarked ? "bookmarked" : ""}`}
          onClick={(e) => {
            e.stopPropagation();
            onBookmark(scheme);
          }}
          title={bookmarked ? "Remove Bookmark" : "Bookmark"}
        >
          {bookmarked ? "🔖" : "🏷️"}
        </button>
      </div>

      <p className="scheme-desc">{scheme.description}</p>

      <div className="card-badges">
        {scheme.category && (
          <span className="badge badge-category">{scheme.category}</span>
        )}
        {scheme.state && scheme.state !== "All" && (
          <span className="badge badge-state">{scheme.state}</span>
        )}
        {scheme.gender && scheme.gender !== "All" && (
          <span className="badge badge-gender">{scheme.gender}</span>
        )}
        {scheme.caste && scheme.caste !== "All" && (
          <span className="badge badge-caste">{scheme.caste}</span>
        )}
        {scheme.bpl && <span className="badge badge-special">{t.bpl}</span>}
        {scheme.disability && <span className="badge badge-special">{t.disability}</span>}
        {scheme.widow && <span className="badge badge-special">{t.widow}</span>}
        {scheme.minority && <span className="badge badge-special">{t.minority}</span>}
      </div>

      {scheme.applyLink && (
        <a
          href={scheme.applyLink}
          target="_blank"
          rel="noreferrer"
          className="apply-link"
          onClick={(e) => e.stopPropagation()}
        >
          {t.applyNow} →
        </a>
      )}
    </div>
  );
}

export default SchemeCard;
