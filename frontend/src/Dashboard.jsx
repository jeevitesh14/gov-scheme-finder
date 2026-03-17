import { useState, useEffect, useCallback } from "react";
import { motion, AnimatePresence } from "framer-motion";
import { 
  X, 
  Info, 
  FileText, 
  CheckCircle2, 
  ExternalLink,
  MapPin,
  User,
  Users,
  Calendar,
  IndianRupee,
  ShieldCheck,
  AlertTriangle
} from "lucide-react";
import HeroSection from "./HeroSection";
import FilterPanel from "./FilterPanel";
import SchemesList from "./SchemesList";
import { schemeService } from "./api";

const DEFAULT_FILTERS = {
  category: "", state: "", gender: "ALL", caste: "ALL",
  age: "All", income: "All",
  disability: false, bpl: false, widow: false, minority: false,
};

function SchemeModal({ scheme, onClose, t }) {
  if (!scheme) return null;

  return (
    <AnimatePresence>
      <div className="modal-overlay" onClick={onClose}>
        <motion.div 
          initial={{ opacity: 0, scale: 0.9, y: 20 }}
          animate={{ opacity: 1, scale: 1, y: 0 }}
          exit={{ opacity: 0, scale: 0.9, y: 20 }}
          className="modal-content" 
          onClick={e => e.stopPropagation()}
        >
          <button className="modal-close" onClick={onClose}>
            <X size={20} />
          </button>
          
          <div className="modal-header">
            <span className="modal-badge">
              <ShieldCheck size={14} />
              {scheme.category || "General"}
            </span>
            <h2>{scheme.name}</h2>
          </div>

          <div className="modal-body">
            <section className="modal-section">
              <h3>
                <Info size={16} />
                About this Scheme
              </h3>
              <p>{scheme.description}</p>
            </section>

            <section className="modal-section">
              <h3>
                <FileText size={16} />
                Eligibility & Details
              </h3>
              <div className="detail-grid">
                <div className="detail-item">
                  <span className="detail-label">State Jurisdiction</span>
                  <span className="detail-value">
                    <MapPin size={14} className="text-primary" />
                    {scheme.state}
                  </span>
                </div>
                <div className="detail-item">
                  <span className="detail-label">Target Gender</span>
                  <span className="detail-value">
                    <User size={14} className="text-primary" />
                    {scheme.gender}
                  </span>
                </div>
                <div className="detail-item">
                  <span className="detail-label">Caste Category</span>
                  <span className="detail-value">
                    <Users size={14} className="text-primary" />
                    {scheme.caste}
                  </span>
                </div>
                {(scheme.ageMin !== null || scheme.ageMax !== null) && (
                  <div className="detail-item">
                    <span className="detail-label">Age Range</span>
                    <span className="detail-value">
                      <Calendar size={14} className="text-primary" />
                      {scheme.ageMin || 0} - {scheme.ageMax || '∞'} Years
                    </span>
                  </div>
                )}
                {scheme.incomeLimit !== null && (
                  <div className="detail-item">
                    <span className="detail-label">Income Threshold</span>
                    <span className="detail-value">
                      <IndianRupee size={14} className="text-primary" />
                      Up to ₹{scheme.incomeLimit.toLocaleString()}
                    </span>
                  </div>
                )}
              </div>
            </section>

            <section className="modal-section">
              <h3>
                <AlertTriangle size={16} />
                Special Provisions
              </h3>
              <div className="card-badges">
                {scheme.bpl && <span className="badge badge-special">{t.bpl}</span>}
                {scheme.disability && <span className="badge badge-special">{t.disability}</span>}
                {scheme.widow && <span className="badge badge-special">{t.widow}</span>}
                {scheme.minority && <span className="badge badge-special">{t.minority}</span>}
                {!scheme.bpl && !scheme.disability && !scheme.widow && !scheme.minority && (
                  <span style={{ color: 'var(--text-muted)', fontSize: '0.9rem' }}>None specified</span>
                )}
              </div>
            </section>
          </div>

          <div className="modal-footer">
            {scheme.applyLink && (
              <a href={scheme.applyLink} target="_blank" rel="noreferrer" className="btn-hero btn-hero-primary" style={{ textDecoration: 'none', justifyContent: 'center' }}>
                Apply on Official Portal
                <ExternalLink size={18} />
              </a>
            )}
          </div>
        </motion.div>
      </div>
    </AnimatePresence>
  );
}

function Dashboard({ bookmarks, onBookmark, onOpenChat, t }) {
  const [schemes, setSchemes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [filters, setFilters] = useState(DEFAULT_FILTERS);
  const [error, setError] = useState(null);
  const [selectedScheme, setSelectedScheme] = useState(null);
  const [pagination, setPagination] = useState({ page: 0, size: 9, totalElements: 0, totalPages: 0 });

  const fetchSchemes = useCallback(async (f, pageNum = 0) => {
    setLoading(true);
    setError(null);
    try {
      const params = {
        page: pageNum,
        size: pagination.size,
        sort: "id,desc"
      };
      if (f.category) params.category = f.category;
      if (f.state) params.state = f.state;
      if (f.gender && f.gender !== "ALL") params.gender = f.gender;
      if (f.caste && f.caste !== "ALL") params.caste = f.caste;
      if (f.disability) params.disability = true;
      if (f.bpl) params.bpl = true;
      if (f.widow) params.widow = true;
      if (f.minority) params.minority = true;

      const response = await schemeService.getSchemes(params);
      const pageData = response.data;
      setSchemes(pageData.content);
      setPagination(prev => ({
        ...prev,
        page: pageData.number,
        totalElements: pageData.totalElements,
        totalPages: pageData.totalPages
      }));
    } catch (err) {
      console.error("Failed to fetch schemes:", err);
      setError(err.message || "Failed to load schemes.");
      setSchemes([]);
    } finally {
      setLoading(false);
    }
  }, [pagination.size]);

  useEffect(() => { 
    fetchSchemes(filters, 0); 
  }, [filters, fetchSchemes]);

  const handlePageChange = (newPage) => {
    fetchSchemes(filters, newPage);
    window.scrollTo({ top: document.getElementById('schemes-section').offsetTop - 100, behavior: 'smooth' });
  };

  const handleFilterChange = (key, value) => {
    setFilters(prev => ({ ...prev, [key]: value }));
  };

  const handleReset = () => {
    setFilters(DEFAULT_FILTERS);
  };

  return (
    <div style={{ background: '#f8fafc' }}>
      <HeroSection onOpenChat={onOpenChat} t={t} />
      <div className="main-layout" id="schemes-section">
        <FilterPanel
          filters={filters}
          onChange={handleFilterChange}
          onReset={handleReset}
          t={t}
        />
        <div style={{ flex: 1, minWidth: 0 }}>
            {error && <div className="auth-error" style={{ margin: "0 0 1rem 0" }}>{error}</div>}
            <SchemesList
              schemes={schemes}
              loading={loading}
              bookmarks={bookmarks}
              onBookmark={onBookmark}
              onSchemeClick={(scheme) => setSelectedScheme(scheme)}
              pagination={pagination}
              onPageChange={handlePageChange}
              t={t}
            />
        </div>
      </div>
      <SchemeModal 
        scheme={selectedScheme} 
        onClose={() => setSelectedScheme(null)} 
        t={t} 
      />
    </div>
  );
}

export default Dashboard;
