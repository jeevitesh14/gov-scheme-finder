import { useState, useEffect } from "react";
import { schemeService } from "./api";
import { Plus, Edit2, Trash2, LayoutDashboard, LogOut, ChevronLeft, ChevronRight } from "lucide-react";
import SchemeForm from "./SchemeForm";

const SCHEMES_API = "/api/schemes";

function AdminDashboard({ onLogout }) {
  const [schemes, setSchemes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [isFormOpen, setIsFormOpen] = useState(false);
  const [editingScheme, setEditingScheme] = useState(null);
  const [notification, setNotification] = useState(null);
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

  return (
    <div className="admin-dashboard-container">
      {/* Sidebar */}
      <aside className="admin-sidebar">
        <div className="sidebar-brand">
          <LayoutDashboard size={24} />
          <span>Gov Admin</span>
        </div>
        <nav className="sidebar-nav">
          <button className="nav-item active">Management</button>
        </nav>
        <button className="logout-btn" onClick={onLogout}>
          <LogOut size={20} />
          <span>Logout</span>
        </button>
      </aside>

      {/* Main Content */}
      <main className="admin-main">
        <header className="admin-header">
          <h1>Scheme Management</h1>
          <button className="add-btn" onClick={() => { setEditingScheme(null); setIsFormOpen(true); }}>
            <Plus size={20} />
            <span>Add New Scheme</span>
          </button>
        </header>

        {notification && (
          <div className={`admin-notification ${notification.type}`}>
            {notification.msg}
          </div>
        )}

        <div className="admin-table-wrapper">
          {loading ? (
            <div className="admin-loading">Loading schemes...</div>
          ) : (
            <table className="admin-table">
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Category</th>
                  <th>State</th>
                  <th>Age Limit</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {schemes.map((scheme) => (
                  <tr key={scheme.id}>
                    <td>
                      <div className="scheme-name-cell">{scheme.name}</div>
                    </td>
                    <td><span className="badge badge-category">{scheme.category}</span></td>
                    <td>{scheme.state === "All" ? "National" : scheme.state}</td>
                    <td>{scheme.ageMin} - {scheme.ageMax}</td>
                    <td>
                      <div className="table-actions">
                        <button className="edit-btn" onClick={() => { setEditingScheme(scheme); setIsFormOpen(true); }}>
                          <Edit2 size={16} />
                        </button>
                        <button className="delete-btn" onClick={() => handleDelete(scheme.id)}>
                          <Trash2 size={16} />
                        </button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </div>

        {pagination.totalPages > 1 && (
          <div className="admin-pagination">
            <button 
              disabled={pagination.page === 0} 
              onClick={() => fetchSchemes(pagination.page - 1)}
            >
              <ChevronLeft size={18} />
              Previous
            </button>
            <span className="page-info">
              Page {pagination.page + 1} of {pagination.totalPages}
            </span>
            <button 
              disabled={pagination.page === pagination.totalPages - 1} 
              onClick={() => fetchSchemes(pagination.page + 1)}
            >
              Next
              <ChevronRight size={18} />
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
