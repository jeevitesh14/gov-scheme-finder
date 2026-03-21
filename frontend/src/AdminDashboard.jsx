import { useState, useEffect } from "react";
import { schemeService } from "./api";
import { 
  Plus, 
  Edit2, 
  Trash2, 
  LayoutDashboard, 
  LogOut, 
  ChevronLeft, 
  ChevronRight,
  ShieldCheck,
  Search,
  CheckCircle2,
  XCircle,
  FileText
} from "lucide-react";
import SchemeForm from "./SchemeForm";
import { useTranslation } from "react-i18next";

function AdminDashboard({ onLogout }) {
  const { t } = useTranslation();
  const [schemes, setSchemes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [isFormOpen, setIsFormOpen] = useState(false);
  const [editingScheme, setEditingScheme] = useState(null);
  const [notification, setNotification] = useState(null);
  const [searchTerm, setSearchTerm] = useState("");
  const [pagination, setPagination] = useState({ page: 0, size: 10, totalPages: 0 });

  useEffect(() => {
    fetchSchemes(0);
  }, []);

  const fetchSchemes = async (page = 0) => {
    setLoading(true);
    try {
      const response = await schemeService.getSchemes({ page, size: pagination.size, sort: "id,desc" });
      const pageData = response.data;
      setSchemes(pageData.content);
      setPagination(prev => ({ ...prev, page: pageData.number, totalPages: pageData.totalPages }));
    } catch (err) {
      showNotification(err.message || "Error fetching schemes", "error");
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this scheme?")) return;
    try {
      await schemeService.deleteScheme(id);
      showNotification("Scheme deleted successfully", "success");
      fetchSchemes(pagination.page);
    } catch (err) {
      showNotification(err.message || "Error deleting scheme", "error");
    }
  };

  const handleSave = async (schemeData) => {
    try {
      if (editingScheme) {
        await schemeService.updateScheme(editingScheme.id, schemeData);
        showNotification("Scheme updated successfully", "success");
      } else {
        await schemeService.createScheme(schemeData);
        showNotification("Scheme created successfully", "success");
      }
      setIsFormOpen(false);
      setEditingScheme(null);
      fetchSchemes(pagination.page);
    } catch (err) {
      showNotification(err.message || "Error saving scheme", "error");
    }
  };

  const showNotification = (msg, type) => {
    setNotification({ msg, type });
    setTimeout(() => setNotification(null), 3000);
  };

  const filteredSchemes = schemes.filter(s => 
    s.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
    s.category.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="admin-dashboard-container">
      <aside className="admin-sidebar">
        <div className="sidebar-brand">
          <ShieldCheck size={28} />
          <span>GovAdmin</span>
        </div>
        <nav className="sidebar-nav">
          <button className="nav-item active">
            <LayoutDashboard size={18} />
            <span>{t('schemesManagement')}</span>
          </button>
          <button className="nav-item" disabled>
            <FileText size={18} />
            <span>{t('usageStats')}</span>
          </button>
        </nav>
        <button className="logout-btn" onClick={onLogout}>
          <LogOut size={18} />
          <span>{t('logout')}</span>
        </button>
      </aside>

      <main className="admin-main">
        <header className="admin-header">
          <h1>{t('schemeManagement')}</h1>
          <button className="add-btn" onClick={() => { setEditingScheme(null); setIsFormOpen(true); }}>
            <Plus size={20} />
            <span>{t('addNewScheme')}</span>
          </button>
        </header>

        {notification && (
          <div className={`admin-notification ${notification.type}`}>
            {notification.type === "success" ? <CheckCircle2 size={18} /> : <XCircle size={18} />}
            {notification.msg}
          </div>
        )}

        <div className="admin-table-wrapper">
          <div className="admin-card-header" style={{ padding: '1rem 1.5rem', background: '#fff', borderBottom: '1px solid var(--border)', display: 'flex', justifyContent: 'flex-end' }}>
             <div className="search-bar" style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', border: '1px solid var(--border)', padding: '0.5rem 1rem', borderRadius: 'var(--radius-full)', background: '#f8fafc' }}>
               <Search size={16} color="var(--text-muted)" />
               <input 
                 type="text" 
                 placeholder={t('searchSchemes')} 
                 value={searchTerm}
                 onChange={(e) => setSearchTerm(e.target.value)}
                 style={{ border: 'none', background: 'transparent', outline: 'none', fontSize: '0.85rem' }}
               />
             </div>
          </div>
          
          {loading ? (
            <div className="loading">
              <div className="spinner"></div>
              <span>Fetching schemes...</span>
            </div>
          ) : (
            <div className="data-table-wrapper">
              <table className="admin-table">
                <thead>
                  <tr>
                    <th>{t('schemeName')}</th>
                    <th>{t('category')}</th>
                    <th>{t('state')}</th>
                    <th>{t('ageBracket')}</th>
                    <th>{t('actions')}</th>
                  </tr>
                </thead>
                <tbody>
                  {filteredSchemes.map((scheme) => (
                    <tr key={scheme.id}>
                      <td>
                        <div className="scheme-name-cell">{scheme.name}</div>
                      </td>
                      <td>
                        <span className="badge badge-category">{scheme.category}</span>
                      </td>
                      <td>{scheme.state === "All" || !scheme.state ? t('national') : scheme.state}</td>
                      <td>{scheme.ageMin || 0} - {scheme.ageMax || '∞'} yrs</td>
                      <td>
                        <div className="table-actions">
                          <button className="edit-btn" onClick={() => { setEditingScheme(scheme); setIsFormOpen(true); }} title="Edit">
                            <Edit2 size={16} />
                          </button>
                          <button className="delete-btn" onClick={() => handleDelete(scheme.id)} title="Delete">
                            <Trash2 size={16} />
                          </button>
                        </div>
                      </td>
                    </tr>
                  ))}
                  {filteredSchemes.length === 0 && (
                    <tr>
                      <td colSpan="5" style={{ textAlign: 'center', padding: '3rem', color: 'var(--text-muted)' }}>
                        No schemes found matching your search.
                      </td>
                    </tr>
                  )}
                </tbody>
              </table>
            </div>
          )}
        </div>

        {pagination.totalPages > 1 && (
          <div className="admin-pagination">
            <button 
              disabled={pagination.page === 0} 
              onClick={() => fetchSchemes(pagination.page - 1)}
            >
              <ChevronLeft size={16} />
              {t('previous')}
            </button>
            <span style={{ fontSize: '0.85rem', fontWeight: 600, color: 'var(--text-muted)' }}>
              {t('pageOf', { current: pagination.page + 1, total: pagination.totalPages })}
            </span>
            <button 
              disabled={pagination.page === pagination.totalPages - 1} 
              onClick={() => fetchSchemes(pagination.page + 1)}
            >
              {t('next')}
              <ChevronRight size={16} />
            </button>
          </div>
        )}
      </main>

      {isFormOpen && (
        <SchemeForm
          scheme={editingScheme}
          onSave={handleSave}
          onClose={() => { setIsFormOpen(false); setEditingScheme(null); }}
        />
      )}
    </div>
  );
}

export default AdminDashboard;
