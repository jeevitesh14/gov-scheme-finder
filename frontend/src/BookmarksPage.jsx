import { useTranslation } from "react-i18next";
import SchemeCard from "./SchemeCard";
import EmptyState from "./EmptyState";
import { Bookmark } from "lucide-react";

function BookmarksPage({ bookmarks, onBookmark }) {
  const { t } = useTranslation();
  return (
    <div className="bookmarks-page">
      <h2>{t('bookmarkedHeader')}</h2>

      {bookmarks.length === 0 ? (
        <EmptyState 
          icon={Bookmark}
          title={t('noBookmarks')}
          description={t('adjustFilters').split("or")[0]}
        />
      ) : (
        <>
          <p style={{ marginBottom: "1rem", color: "var(--text-secondary)", fontSize: ".9rem" }}>
            {t('savedCount')} <strong>{bookmarks.length}</strong> {t('schemes')}.
          </p>
          <div className="schemes-grid">
            {bookmarks.map(scheme => (
              <SchemeCard
                key={scheme.id}
                scheme={scheme}
                bookmarked={true}
                onBookmark={onBookmark}
              />
            ))}
          </div>
        </>
      )}
    </div>
  );
}

export default BookmarksPage;
