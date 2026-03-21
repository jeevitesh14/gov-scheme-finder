import { 
  Filter, 
  RotateCcw, 
  User, 
  Briefcase, 
  MapPin, 
  Users, 
  Calendar, 
  IndianRupee, 
  CheckCircle2 
} from "lucide-react";
import { useTranslation } from "react-i18next";

const AGE_RANGES = ["All", "0-18", "19-35", "36-50", "51-65", "65+"];
const INCOMES = ["All", "Below 1 Lakh", "1-3 Lakhs", "3-6 Lakhs", "Above 6 Lakhs"];

function FilterPanel({ filters, onChange, onReset }) {
  const { t } = useTranslation();
  const CATEGORIES = [
    { label: t('all'), value: "" },
    { label: t('agriculture'), value: "AGRICULTURE" },
    { label: t('education'), value: "EDUCATION" },
    { label: t('health'), value: "HEALTH" },
    { label: t('housing'), value: "HOUSING" },
    { label: t('finance'), value: "FINANCE" },
    { label: t('food'), value: "FOOD" },
    { label: t('socialWelfare'), value: "SOCIAL_WELFARE" },
    { label: t('womenChildWelfare'), value: "WOMEN_CHILD_WELFARE" },
    { label: t('employment'), value: "EMPLOYMENT" },
    { label: t('skillDevelopment'), value: "SKILL_DEVELOPMENT" },
    { label: t('entrepreneurship'), value: "ENTREPRENEURSHIP" },
    { label: t('other'), value: "OTHER" }
  ];
  const STATES = [
    t('all'), "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana", 
    "Himachal Pradesh", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", 
    "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", 
    "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal"
  ];
  const GENDERS = [
    { label: t('all'), value: "ALL" },
    { label: t('male'), value: "MALE" },
    { label: t('female'), value: "FEMALE" }
  ];
  const CASTES = [
    { label: t('all'), value: "ALL" },
    { label: t('general'), value: "GENERAL" },
    { label: t('obc'), value: "OBC" },
    { label: t('sc'), value: "SC" },
    { label: t('st'), value: "ST" }
  ];

  const filterGroups = [
    { id: "category", label: t('category'), icon: Briefcase, options: CATEGORIES },
    { id: "state", label: t('state'), icon: MapPin, options: STATES.map(s => ({ label: s, value: s === t('all') ? "" : s })) },
    { id: "gender", label: t('gender'), icon: User, options: GENDERS },
    { id: "caste", label: t('caste'), icon: Users, options: CASTES },
    { id: "age", label: t('age'), icon: Calendar, options: AGE_RANGES.map(a => ({ label: a === "All" ? t('all') : a, value: a })) },
    { id: "income", label: t('income'), icon: IndianRupee, options: INCOMES.map(i => ({ label: i === "All" ? t('all') : i, value: i })) },
  ];

  return (
    <aside className="filter-panel">
      <div className="filter-header">
        <Filter size={20} className="text-secondary" />
        <h3>{t('filterSchemes')}</h3>
      </div>

      {filterGroups.map((group) => (
        <div key={group.id} className="filter-group">
          <label className="filter-group-label">
            <group.icon size={14} />
            {group.label}
          </label>
          <select 
            className="filter-select" 
            value={filters[group.id]} 
            onChange={e => onChange(group.id, e.target.value)}
          >
            {group.options.map(opt => (
              <option key={opt.value} value={opt.value}>{opt.label}</option>
            ))}
          </select>
        </div>
      ))}

      <div className="filter-divider" />

      <div className="special-conditions">
        <h4>
          <CheckCircle2 size={14} />
          {t('specialConditions')}
        </h4>
        <div className="checkbox-group">
          {[
            { key: "disability", label: t('disability') },
            { key: "bpl", label: t('bpl') },
            { key: "widow", label: t('widow') },
            { key: "minority", label: t('minority') },
          ].map(({ key, label }) => (
            <label key={key} className={`checkbox-item ${filters[key] ? 'checked' : ''}`}>
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

      <button className="filter-reset" onClick={onReset}>
        <RotateCcw size={16} />
        {t('resetFilters')}
      </button>
    </aside>
  );
}

export default FilterPanel;
