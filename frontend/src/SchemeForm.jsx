import { useState, useEffect } from "react";
import { X } from "lucide-react";

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
        <div className="modal-header">
          <h2>{scheme ? "Edit Scheme" : "New Scheme"}</h2>
          <button className="close-modal" onClick={onClose}><X size={24} /></button>
        </div>
        <form className="admin-form" onSubmit={handleSubmit}>
          <div className="form-grid">
            <div className="form-group span-2">
              <label>Scheme Name</label>
              <input name="name" value={formData.name} onChange={handleChange} required placeholder="Enter scheme name" />
            </div>
            <div className="form-group span-2">
              <label>Description</label>
              <textarea name="description" value={formData.description} onChange={handleChange} required placeholder="Brief description of the scheme" />
            </div>
            <div className="form-group">
              <label>Category</label>
              <select name="category" value={formData.category} onChange={handleChange}>
                {CATEGORIES.map(c => <option key={c.value} value={c.value}>{c.label}</option>)}
              </select>
            </div>
            <div className="form-group">
              <label>State</label>
              <select name="state" value={formData.state} onChange={handleChange}>
                {STATES.map(s => <option key={s} value={s}>{s}</option>)}
              </select>
            </div>
            <div className="form-group">
              <label>Gender</label>
              <select name="gender" value={formData.gender} onChange={handleChange}>
                {GENDERS.map(g => <option key={g.value} value={g.value}>{g.label}</option>)}
              </select>
            </div>
            <div className="form-group">
              <label>Caste</label>
              <select name="caste" value={formData.caste} onChange={handleChange}>
                {CASTES.map(c => <option key={c.value} value={c.value}>{c.label}</option>)}
              </select>
            </div>
            <div className="form-group">
              <label>Min Age</label>
              <input type="number" name="ageMin" value={formData.ageMin} onChange={handleChange} required />
            </div>
            <div className="form-group">
              <label>Max Age</label>
              <input type="number" name="ageMax" value={formData.ageMax} onChange={handleChange} required />
            </div>
            <div className="form-group">
              <label>Income Limit (₹)</label>
              <input type="number" name="incomeLimit" value={formData.incomeLimit} onChange={handleChange} required />
            </div>
            <div className="form-group">
              <label>Apply Link</label>
              <input name="applyLink" value={formData.applyLink} onChange={handleChange} required placeholder="https://..." />
            </div>
          </div>

          <div className="checkbox-grid">
            <label className="checkbox-item">
              <input type="checkbox" name="disability" checked={formData.disability} onChange={handleChange} />
              <span>Disability</span>
            </label>
            <label className="checkbox-item">
              <input type="checkbox" name="bpl" checked={formData.bpl} onChange={handleChange} />
              <span>BPL</span>
            </label>
            <label className="checkbox-item">
              <input type="checkbox" name="widow" checked={formData.widow} onChange={handleChange} />
              <span>Widow</span>
            </label>
            <label className="checkbox-item">
              <input type="checkbox" name="minority" checked={formData.minority} onChange={handleChange} />
              <span>Minority</span>
            </label>
          </div>

          <div className="form-actions">
            <button type="button" className="btn-secondary" onClick={onClose}>Cancel</button>
            <button type="submit" className="btn-primary">Save Scheme</button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default SchemeForm;
