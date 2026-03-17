import { ChevronLeft, ChevronRight } from "lucide-react";
import SchemeCard from "./SchemeCard";

function SchemesList({ 
    schemes, 
    loading, 
    bookmarks, 
    onBookmark, 
    onSchemeClick, 
    pagination, 
    onPageChange, 
    t 
}) {
  if (loading) {
    return (
      <div className="loading">
        <div className="spinner"></div>
        Loading schemes...
      </div>
    );
  }

  return (
    <section className="schemes-section">
      <div className="schemes-header">
        <h2>{t.allSchemes}</h2>
        <span className="schemes-count">
          {pagination ? (
            <>Showing {pagination.page * pagination.size + 1}-{Math.min((pagination.page + 1) * pagination.size, pagination.totalElements)} of {pagination.totalElements} {t.schemesAvailable}</>
          ) : (
            <>{schemes.length} {t.schemesAvailable}</>
          )}
        </span>
      </div>

      {schemes.length === 0 ? (
        <div className="empty-state">
          <div className="empty-icon">📭</div>
          <h3>No schemes found</h3>
          <p>
            Try adjusting your filters or visit our chat to get personalized
            recommendations.
          </p>
        </div>
      ) : (
        <>
          <div className="schemes-grid">
            {schemes.map(scheme => (
              <SchemeCard
                key={scheme.id}
                scheme={scheme}
                bookmarked={bookmarks.some(b => b.id === scheme.id)}
                onBookmark={onBookmark}
                onClick={() => onSchemeClick(scheme)}
                t={t}
              />
            ))}
          </div>

          {pagination && pagination.totalPages > 1 && (
            <div className="pagination">
              <button 
                className="page-btn" 
                disabled={pagination.page === 0} 
                onClick={() => onPageChange(pagination.page - 1)}
              >
                <ChevronLeft size={18} />
                {t.previous || "Previous"}
              </button>
              
              <div className="page-numbers">
                {[...Array(pagination.totalPages)].map((_, i) => (
                  <button 
                    key={i} 
                    className={`page-num ${pagination.page === i ? 'active' : ''}`}
                    onClick={() => onPageChange(i)}
                  >
                    {i + 1}
                  </button>
                ))}
              </div>

              <button 
                className="page-btn" 
                disabled={pagination.page === pagination.totalPages - 1} 
                onClick={() => onPageChange(pagination.page + 1)}
              >
                {t.next || "Next"}
                <ChevronRight size={18} />
              </button>
            </div>
          )}
        </>
      )}
    </section>
  );
}

export default SchemesList;
