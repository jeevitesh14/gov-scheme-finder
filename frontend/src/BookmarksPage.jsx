import SchemeCard from "./SchemeCard";

function BookmarksPage({ bookmarks, onBookmark, t }) {
  return (
    <div className="bookmarks-page">
      <h2>{t.bookmarkedHeader}</h2>

      {bookmarks.length === 0 ? (
        <div className="empty-state">
          <div className="empty-icon">🏷️</div>
          <h3>{t.noBookmarks}</h3>
          <p>
            {t.adjustFilters.split("or")[0]} 
          </p>
        </div>
      ) : (
        <>
          <p style={{ marginBottom: "1rem", color: "var(--text-secondary)", fontSize: ".9rem" }}>
            {t.savedCount} <strong>{bookmarks.length}</strong> {t.schemes}.
          </p>
          <div className="schemes-grid">
            {bookmarks.map(scheme => (
              <SchemeCard
                key={scheme.id}
                scheme={scheme}
                bookmarked={true}
                onBookmark={onBookmark}
                t={t}
              />
            ))}
          </div>
        </>
      )}
    </div>
  );
}

export default BookmarksPage;
