import { useTranslation } from "react-i18next";
import SchemeCard from "./SchemeCard";
import SkeletonCard from "./SkeletonCard";
import EmptyState from "./EmptyState";
import { Search, ChevronLeft, ChevronRight } from "lucide-react";

function SchemesList({ 
    schemes, 
    loading, 
    onSchemeClick, 
    pagination, 
    onPageChange
}) {
  const { t } = useTranslation();
  
  if (loading) {
    return (
      <div className="schemes-section">
        <div className="schemes-header">
          <div className="skeleton" style={{ height: '32px', width: '200px' }}></div>
          <div className="skeleton" style={{ height: '20px', width: '150px' }}></div>
        </div>
        <div className="schemes-grid">
          {[...Array(6)].map((_, i) => <SkeletonCard key={i} />)}
        </div>
      </div>
    );
  }

  return (
    <section className="schemes-section">
      <div className="schemes-header">
        <h2>{t('allSchemes')}</h2>
        <span className="schemes-count">
          {pagination ? (
            <>{t('showingResults', { 
              start: pagination.page * pagination.size + 1, 
              end: Math.min((pagination.page + 1) * pagination.size, pagination.totalElements), 
              total: pagination.totalElements, 
              label: t('schemes') 
            })}</>
          ) : (
            <>{schemes.length} {t('schemesAvailable')}</>
          )}
        </span>
      </div>

      {schemes.length === 0 ? (
        <EmptyState 
          icon={Search}
          title={t('noSchemesFound')}
          description={t('emptyStateDescription')}
        />
      ) : (
        <>
          <div className="schemes-grid">
            {schemes.map(scheme => (
              <SchemeCard
                key={scheme.id}
                scheme={scheme}
                onClick={() => onSchemeClick(scheme)}
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
                <ChevronLeft size={18} className="chevron-left" />
                {t('previous') || "Previous"}
              </button>
              
              <div className="page-numbers">
                {(() => {
                  const current = pagination.page;
                  const total = pagination.totalPages;
                  const pages = [];
                  
                  if (total <= 5) {
                    for (let i = 0; i < total; i++) pages.push(i);
                  } else {
                    if (current <= 2) {
                      pages.push(0, 1, 2, 3, 4);
                    } else if (current >= total - 3) {
                      for (let i = total - 5; i < total; i++) pages.push(i);
                    } else {
                      pages.push(current - 2, current - 1, current, current + 1, current + 2);
                    }
                  }
                  
                  return pages.map(i => (
                    <button 
                      key={i} 
                      className={`page-num ${pagination.page === i ? 'active' : ''}`}
                      onClick={() => onPageChange(i)}
                      aria-label={`Page ${i + 1}`}
                      aria-current={pagination.page === i ? 'page' : undefined}
                    >
                      {i + 1}
                    </button>
                  ));
                })()}
              </div>

              <button 
                className="page-btn" 
                disabled={pagination.page === pagination.totalPages - 1} 
                onClick={() => onPageChange(pagination.page + 1)}
              >
                {t('next') || "Next"}
                <ChevronRight size={18} className="chevron-right" />
              </button>
            </div>
          )}
        </>
      )}
    </section>
  );
}

export default SchemesList;
