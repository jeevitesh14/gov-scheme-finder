import { useState, useEffect } from "react";
import { X, Save, AlertCircle } from "lucide-react";

const CATEGORIES = [
  { label: "Agriculture", value: "AGRICULTURE" },
  { label: "Education", value: "EDUCATION" },
  { label: "Health", value: "HEALTH" },
  { label: "Housing", value: "HOUSING" },
  { label: "Finance", value: "FINANCE" },
  { label: "Food", value: "FOOD" },
  { label: "Social Welfare", value: "SOCIAL_WELFARE" },
  { label: "Entrepreneurship", value: "ENTREPRENEURSHIP" },
  { label: "Other", value: "OTHER" }
];

const GENDERS = [
  { label: "All", value: "ALL" },
  { label: "Male", value: "MALE" },
  { label: "Female", value: "FEMALE" }
];

const CASTES = [
  { label: "All", value: "ALL" },
  { label: "General", value: "GENERAL" },
  { label: "OBC", value: "OBC" },
  { label: "SC", value: "SC" },
  { label: "ST", value: "ST" }
];

const STATES = [
  "All", "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", 
  "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka", 
  "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", 
  "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", 
  "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal"
];

function SchemeForm({ scheme, onSave, onClose }) {
  const [formData, setFormData] = useState({
    name: "",
    description: "",
    category: "AGRICULTURE",
    state: "All",
    gender: "ALL",
    caste: "ALL",
    ageMin: 0,
    ageMax: 100,
    incomeLimit: 1000000,
    disability: false,
    bpl: false,
    widow: false,
    minority: false,
    applyLink: "",
  });

  useEffect(() => {
    if (scheme) {
      setFormData({
        ...scheme,
        ageMin: scheme.ageMin ?? 0,
        ageMax: scheme.ageMax ?? 100,
        incomeLimit: scheme.incomeLimit ?? 1000000,
      });
    }
  }, [scheme]);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSave(formData);
  };

  return (
    <div className="admin-modal-overlay">
      <div className="admin-modal">
        <div className="admin-modal-header">
          <h2>{scheme ? "Edit Scheme" : "Create New Scheme"}</h2>
          <button className="btn-cancel" style={{ padding: '0.5rem', width: '36px', height: '36px', display: 'flex', alignItems: 'center', justifyContent: 'center' }} onClick={onClose}>
            <X size={20} />
          </button>
        </div>

        <div className="admin-modal-body">
          <form id="scheme-form" onSubmit={handleSubmit}>
            <div className="form-grid">
              <div className="form-group full-width" style={{ gridColumn: 'span 2' }}>
                <label>Scheme Name</label>
                <input 
                  className="form-control"
                  name="name" 
                  value={formData.name} 
                  onChange={handleChange} 
                  required 
                  placeholder="e.g. PM-Kisan Samman Nidhi" 
                />
              </div>

              <div className="form-group full-width" style={{ gridColumn: 'span 2' }}>
                <label>Detailed Description</label>
                <textarea 
                  className="form-control"
                  name="description" 
                  value={formData.description} 
                  onChange={handleChange} 
                  required 
                  placeholder="Explain what the scheme offers and its primary goals..." 
                />
              </div>

              <div className="form-group">
                <label>Primary Category</label>
                <select className="form-control" name="category" value={formData.category} onChange={handleChange}>
                  {CATEGORIES.map(c => <option key={c.value} value={c.value}>{c.label}</option>)}
                </select>
              </div>

              <div className="form-group">
                <label>State Jurisdiction</label>
                <select className="form-control" name="state" value={formData.state} onChange={handleChange}>
                  {STATES.map(s => <option key={s} value={s}>{s}</option>)}
                </select>
              </div>

              <div className="form-group">
                <label>Target Gender</label>
                <select className="form-control" name="gender" value={formData.gender} onChange={handleChange}>
                  {GENDERS.map(g => <option key={g.value} value={g.value}>{g.label}</option>)}
                </select>
              </div>

              <div className="form-group">
                <label>Caste Eligibility</label>
                <select className="form-control" name="caste" value={formData.caste} onChange={handleChange}>
                  {CASTES.map(c => <option key={c.value} value={c.value}>{c.label}</option>)}
                </select>
              </div>

              <div className="form-group">
                <label>Min Age (Years)</label>
                <input className="form-control" type="number" name="ageMin" value={formData.ageMin} onChange={handleChange} required />
              </div>

              <div className="form-group">
                <label>Max Age (Years)</label>
                <input className="form-control" type="number" name="ageMax" value={formData.ageMax} onChange={handleChange} required />
              </div>

              <div className="form-group">
                <label>Annual Income Limit (₹)</label>
                <input className="form-control" type="number" name="incomeLimit" value={formData.incomeLimit} onChange={handleChange} required />
              </div>

              <div className="form-group">
                <label>Application Portal URL</label>
                <input 
                  className="form-control"
                  name="applyLink" 
                  value={formData.applyLink} 
                  onChange={handleChange} 
                  required 
                  placeholder="https://india.gov.in/scheme-portal" 
                />
              </div>
            </div>

            <div style={{ marginTop: '2rem' }}>
              <label style={{ fontSize: '0.8rem', fontWeight: 700, color: 'var(--text-muted)', textTransform: 'uppercase', letterSpacing: '0.05em', marginBottom: '1rem', display: 'block' }}>
                Special Provisions & Reservations
              </label>
              <div className="form-checkbox-group">
                <label className="checkbox-item" style={{ display: 'flex', alignItems: 'center', gap: '0.75rem', cursor: 'pointer', background: '#f8fafc', padding: '0.75rem 1rem', borderRadius: '12px', border: '1px solid var(--border)' }}>
                  <input type="checkbox" name="disability" checked={formData.disability} onChange={handleChange} />
                  <span style={{ fontSize: '0.9rem', fontWeight: 600 }}>Disability Benefits</span>
                </label>
                <label className="checkbox-item" style={{ display: 'flex', alignItems: 'center', gap: '0.75rem', cursor: 'pointer', background: '#f8fafc', padding: '0.75rem 1rem', borderRadius: '12px', border: '1px solid var(--border)' }}>
                  <input type="checkbox" name="bpl" checked={formData.bpl} onChange={handleChange} />
                  <span style={{ fontSize: '0.9rem', fontWeight: 600 }}>BPL Holders</span>
                </label>
                <label className="checkbox-item" style={{ display: 'flex', alignItems: 'center', gap: '0.75rem', cursor: 'pointer', background: '#f8fafc', padding: '0.75rem 1rem', borderRadius: '12px', border: '1px solid var(--border)' }}>
                  <input type="checkbox" name="widow" checked={formData.widow} onChange={handleChange} />
                  <span style={{ fontSize: '0.9rem', fontWeight: 600 }}>Widow Support</span>
                </label>
                <label className="checkbox-item" style={{ display: 'flex', alignItems: 'center', gap: '0.75rem', cursor: 'pointer', background: '#f8fafc', padding: '0.75rem 1rem', borderRadius: '12px', border: '1px solid var(--border)' }}>
                  <input type="checkbox" name="minority" checked={formData.minority} onChange={handleChange} />
                  <span style={{ fontSize: '0.9rem', fontWeight: 600 }}>Minority Groups</span>
                </label>
              </div>
            </div>
          </form>
        </div>

        <div className="admin-form-footer">
          <button type="button" className="btn-cancel" onClick={onClose}>
            Discard Changes
          </button>
          <button type="submit" form="scheme-form" className="btn-save">
            <Save size={18} style={{ marginRight: '0.5rem', display: 'inline', verticalAlign: 'middle' }} />
            {scheme ? "Update Scheme" : "Publish Scheme"}
          </button>
        </div>
      </div>
    </div>
  );
}

export default SchemeForm;
