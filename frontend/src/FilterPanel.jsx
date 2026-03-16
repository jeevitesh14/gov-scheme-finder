const AGE_RANGES = ["All", "0-18", "19-35", "36-50", "51-65", "65+"];
const INCOMES = ["All", "Below 1 Lakh", "1-3 Lakhs", "3-6 Lakhs", "Above 6 Lakhs"];

function FilterPanel({ filters, onChange, onReset, t }) {
  const CATEGORIES = [
    { label: "All", value: "" },
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
  const STATES = [
    "All", "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana", 
    "Himachal Pradesh", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", 
    "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", 
    "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal"
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

  return (
    <aside className="filter-panel">
      <h3>{t.filterSchemes}</h3>

      <div className="filter-group">
        <label>{t.age}</label>
        <select className="filter-select" value={filters.age} onChange={e => onChange("age", e.target.value)}>
          {AGE_RANGES.map(a => <option key={a}>{a}</option>)}
        </select>
      </div>

      <div className="filter-group">
        <label>{t.income}</label>
        <select className="filter-select" value={filters.income} onChange={e => onChange("income", e.target.value)}>
          {INCOMES.map(i => <option key={i}>{i}</option>)}
        </select>
      </div>

      <div className="filter-group">
        <label>{t.category}</label>
        <select className="filter-select" value={filters.category} onChange={e => onChange("category", e.target.value)}>
          {CATEGORIES.map(c => <option key={c.value} value={c.value}>{c.label}</option>)}
        </select>
      </div>

      <div className="filter-group">
        <label>{t.state}</label>
        <select className="filter-select" value={filters.state} onChange={e => onChange("state", e.target.value)}>
          {STATES.map(s => <option key={s} value={s === "All" ? "" : s}>{s}</option>)}
        </select>
      </div>

      <div className="filter-group">
        <label>{t.caste}</label>
        <select className="filter-select" value={filters.caste} onChange={e => onChange("caste", e.target.value)}>
          {CASTES.map(c => <option key={c.value} value={c.value}>{c.label}</option>)}
        </select>
      </div>

      <div className="filter-group">
        <label>{t.gender}</label>
        <select className="filter-select" value={filters.gender} onChange={e => onChange("gender", e.target.value)}>
          {GENDERS.map(g => <option key={g.value} value={g.value}>{g.label}</option>)}
        </select>
      </div>

      <hr className="filter-divider" />

      <div className="special-conditions">
        <h4>{t.specialConditions}</h4>
        <div className="checkbox-group">
          {[
            { key: "disability", label: t.disability },
            { key: "bpl", label: t.bpl },
            { key: "widow", label: t.widow },
            { key: "minority", label: t.minority },
          ].map(({ key, label }) => (
            <label key={key} className="checkbox-item">
              <input
                type="checkbox"
                checked={filters[key] || false}
                onChange={e => onChange(key, e.target.checked)}
              />
              {label}
            </label>
          ))}
        </div>
      </div>

      <button className="filter-reset" onClick={onReset}>↺ {t.resetFilters}</button>
    </aside>
  );
}

export default FilterPanel;
