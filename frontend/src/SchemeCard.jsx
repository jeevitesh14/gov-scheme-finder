import { 
  ChevronRight, 
  Tractor, 
  GraduationCap, 
  Stethoscope, 
  Home, 
  Wallet, 
  Utensils, 
  HeartHandshake, 
  Rocket, 
  Compass,
  MapPin,
  Users,
  User as UserIcon
} from "lucide-react";
import { motion } from "framer-motion";
import { useTranslation } from "react-i18next";

const CATEGORY_ICONS = {
  AGRICULTURE: Tractor,
  EDUCATION: GraduationCap,
  HEALTH: Stethoscope,
  HOUSING: Home,
  FINANCE: Wallet,
  FOOD: Utensils,
  SOCIAL_WELFARE: HeartHandshake,
  ENTREPRENEURSHIP: Rocket,
  OTHER: Compass
};

function SchemeCard({ scheme, onClick }) {
  const { t } = useTranslation();
  const Icon = CATEGORY_ICONS[scheme.category] || Compass;

  return (
    <motion.div 
      initial={{ opacity: 0, y: 20 }}
      whileInView={{ opacity: 1, y: 0 }}
      viewport={{ once: true }}
      className="scheme-card" 
      onClick={onClick}
    >
      <div className="card-top">
        <div className="card-icon-wrapper">
          <Icon size={24} />
        </div>
      </div>

      <div className="card-content">
        <h3 className="scheme-name">{scheme.name}</h3>
        <p className="scheme-desc">{scheme.description}</p>
      </div>

      <div className="card-badges">
        {scheme.category && (
          <span className="badge badge-category">
            <Icon size={12} />
            {scheme.category.replace('_', ' ')}
          </span>
        )}
        {scheme.state && scheme.state !== "All" && (
          <span className="badge badge-state">
            <MapPin size={12} />
            {scheme.state}
          </span>
        )}
        {scheme.gender && scheme.gender !== "ALL" && (
          <span className="badge badge-gender">
            <UserIcon size={12} />
            {scheme.gender}
          </span>
        )}
      </div>

      <div style={{ marginTop: 'auto', display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
        {scheme.applyLink && (
          <a
            href={scheme.applyLink}
            target="_blank"
            rel="noreferrer"
            className="apply-link"
            onClick={(e) => e.stopPropagation()}
          >
            {t('applyNow')}
            <ChevronRight size={16} />
          </a>
        )}
      </div>
    </motion.div>
  );
}

export default SchemeCard;
