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
  Search,
  ShieldCheck,
  AlertTriangle,
  IndianRupee
} from "lucide-react";
import { useTranslation } from "react-i18next";

import HeroSection from "./HeroSection";
import FilterPanel from "./FilterPanel";
import SchemesList from "./SchemesList";
import SchemeCard from "./SchemeCard";
import { schemeService, applicationService } from "./api";

const DEFAULT_FILTERS = {
  category: "", state: "", gender: "ALL", caste: "ALL",
  age: "All", income: "All",
  disability: false, bpl: false, widow: false, minority: false,
  search: "",
};

function SchemeModal({ scheme, onClose, onApply }) {
  const { t } = useTranslation();
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
                {t('aboutScheme')}
              </h3>
              <p>{scheme.description}</p>
            </section>

            <section className="modal-section">
              <h3>
                <FileText size={16} />
                {t('eligibilityDetails')}
              </h3>
              <div className="detail-grid">
                <div className="detail-item">
                  <span className="detail-label">{t('stateJurisdiction')}</span>
                  <span className="detail-value">
                    <MapPin size={14} className="text-primary" />
                    {scheme.state}
                  </span>
                </div>
                <div className="detail-item">
                  <span className="detail-label">{t('targetGender')}</span>
                  <span className="detail-value">
                    <User size={14} className="text-primary" />
                    {scheme.gender}
                  </span>
                </div>
                <div className="detail-item">
                  <span className="detail-label">{t('casteCategory')}</span>
                  <span className="detail-value">
                    <Users size={14} className="text-primary" />
                    {scheme.caste}
                  </span>
                </div>
                {(scheme.ageMin !== null || scheme.ageMax !== null) && (
                  <div className="detail-item">
                    <span className="detail-label">{t('ageRange')}</span>
                    <span className="detail-value">
                      <Calendar size={14} className="text-primary" />
                      {scheme.ageMin || 0} - {scheme.ageMax || '∞'} {t('years')}
                    </span>
                  </div>
                )}
                {scheme.incomeLimit !== null && (
                  <div className="detail-item">
                    <span className="detail-label">{t('incomeThreshold')}</span>
                    <span className="detail-value">
                      <IndianRupee size={14} className="text-primary" />
                      {t('upTo')} ₹{scheme.incomeLimit.toLocaleString()}
                    </span>
                  </div>
                )}
              </div>
            </section>

            <section className="modal-section">
              <h3>
                <AlertTriangle size={16} />
                {t('specialProvisions')}
              </h3>
              <div className="card-badges">
                {scheme.bpl && <span className="badge badge-special">{t('bpl')}</span>}
                {scheme.disability && <span className="badge badge-special">{t('disability')}</span>}
                {scheme.widow && <span className="badge badge-special">{t('widow')}</span>}
                {scheme.minority && <span className="badge badge-special">{t('minority')}</span>}
                {!scheme.bpl && !scheme.disability && !scheme.widow && !scheme.minority && (
                  <span style={{ color: 'var(--text-muted)', fontSize: '0.9rem' }}>{t('noneSpecified')}</span>
                )}
              </div>
            </section>
          </div>

          <div className="modal-footer" style={{ gap: '1rem' }}>
            <button 
              className="btn-hero btn-hero-primary" 
              style={{ flex: 1, justifyContent: 'center' }}
              onClick={() => onApply(scheme)}
            >
              {t('applyNow')}
              <CheckCircle2 size={18} />
            </button>
            {scheme.applyLink && (
              <a href={scheme.applyLink} target="_blank" rel="noreferrer" className="btn-hero btn-hero-outline" style={{ flex: 1, textDecoration: 'none', justifyContent: 'center' }}>
                Official Portal
                <ExternalLink size={18} />
              </a>
            )}
          </div>
        </motion.div>
      </div>
    </AnimatePresence>
  );
}

function Dashboard({ bookmarks, onBookmark, onOpenChat }) {
  const { t } = useTranslation();
  const [schemes, setSchemes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [filters, setFilters] = useState(DEFAULT_FILTERS);
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);
  const [selectedScheme, setSelectedScheme] = useState(null);
  const [pagination, setPagination] = useState({ page: 0, size: 9, totalElements: 0, totalPages: 0 });
  const [eligibleSchemes, setEligibleSchemes] = useState([]);
  const [eligibleLoading, setEligibleLoading] = useState(false);

  const fetchEligibleSchemes = async () => {
    setEligibleLoading(true);
    try {
      const response = await schemeService.getEligibleSchemes({ size: 4 });
      setEligibleSchemes(response.data.content);
    } catch (err) {
      console.error("Failed to fetch eligible schemes:", err);
    } finally {
      setEligibleLoading(false);
    }
  };

  const fetchSchemes = useCallback(async (f, pageNum = 0) => {
    setLoading(true);
    setError(null);
    try {
      const params = {
        page: pageNum,
        size: pagination.size,
        sort: "id,desc"
      };
      if (f.search) params.keyword = f.search;
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
    fetchEligibleSchemes();
  }, [filters, fetchSchemes]);

  const handleApply = async (scheme) => {
    try {
      await applicationService.applyForScheme(scheme.id);
      setSuccess(`Successfully applied for ${scheme.name}!`);
      setSelectedScheme(null);
      setTimeout(() => setSuccess(null), 5000);
    } catch (err) {
      setError(err.message || "Failed to submit application.");
      setTimeout(() => setError(null), 5000);
    }
  };

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
    <div style={{ background: 'var(--bg)', minHeight: '100vh', transition: 'var(--transition)' }}>
      <HeroSection 
        onOpenChat={onOpenChat} 
        searchQuery={filters.search}
        onSearchChange={(val) => handleFilterChange('search', val)}
      />
      

      <div style={{ maxWidth: '1200px', margin: '2rem auto', padding: '0 2rem' }}>
        {eligibleSchemes.length > 0 && (
          <section className="eligible-section">
            <div className="eligible-header">
              <ShieldCheck size={24} />
              <h2>{t('recommendedForYou')}</h2>
            </div>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
              {eligibleSchemes.map(scheme => (
                <div key={scheme.id} className="relative">
                  <SchemeCard 
                    scheme={{...scheme, isEligible: true}} 
                    onClick={() => setSelectedScheme(scheme)}
                  />
                  <div className="eligible-badge">
                    <CheckCircle2 size={10} />
                    {t('youAreEligible')}
                  </div>
                </div>
              ))}
            </div>
          </section>
        )}
      </div>

      <div className="main-layout" id="schemes-section">
        <FilterPanel
          filters={filters}
          onChange={handleFilterChange}
          onReset={handleReset}
        />
        <div style={{ flex: 1, minWidth: 0 }}>
            {error && (
              <motion.div initial={{opacity:0}} animate={{opacity:1}} className="auth-error" style={{ margin: "0 0 1rem 0" }}>
                {error}
              </motion.div>
            )}
            {success && (
              <motion.div initial={{opacity:0}} animate={{opacity:1}} className="auth-success" style={{ margin: "0 0 1rem 0" }}>
                {success}
              </motion.div>
            )}
            <SchemesList
              schemes={schemes}
              loading={loading}
              onSchemeClick={(scheme) => setSelectedScheme(scheme)}
              pagination={pagination}
              onPageChange={handlePageChange}
            />
        </div>
      </div>
      <SchemeModal 
        scheme={selectedScheme} 
        onClose={() => setSelectedScheme(null)} 
        onApply={handleApply}
      />
    </div>
  );
}

export default Dashboard;

