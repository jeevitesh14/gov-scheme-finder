import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { motion } from "framer-motion";
import { ShieldCheck, UserPlus, Mail, Lock, User, AlertCircle, ArrowRight } from "lucide-react";
import { authService } from "./api";
import { useTranslation } from "react-i18next";

function Register() {
  const { t } = useTranslation();
  const [user, setUser] = useState({ name: "", email: "", password: "" });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => setUser({ ...user, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);
    try {
      await authService.register(user);
      navigate("/login");
    } catch (err) {
      setError(err.message || "An error occurred during registration.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-page">
      <motion.div 
        initial={{ opacity: 0, scale: 0.95 }}
        animate={{ opacity: 1, scale: 1 }}
        className="auth-card"
      >
        <div className="auth-logo">
          <ShieldCheck size={48} className="text-primary" style={{ margin: '0 auto 1.5rem' }} />
          <h1>{t('joinGovScheme')}</h1>
          <p>{t('discoverBenefits')}</p>
        </div>

        <h2 style={{ textAlign: 'center', marginBottom: '1.5rem', fontSize: '1.5rem', fontWeight: 800 }}>{t('createAccount')}</h2>

        {error && (
          <div className="auth-error">
            <AlertCircle size={16} />
            {error}
          </div>
        )}

        <form onSubmit={handleSubmit} className="auth-form">
          <div className="form-group">
            <label>{t('fullName')}</label>
            <div style={{ position: 'relative' }}>
              <User size={18} style={{ position: 'absolute', left: '1rem', top: '50%', transform: 'translateY(-50%)', color: 'var(--text-muted)' }} />
              <input
                id="register-name"
                className="form-input"
                style={{ paddingLeft: '2.75rem' }}
                name="name"
                placeholder="John Doe"
                value={user.name}
                onChange={handleChange}
                required
              />
            </div>
          </div>
          <div className="form-group">
            <label>{t('emailAddress')}</label>
            <div style={{ position: 'relative' }}>
              <Mail size={18} style={{ position: 'absolute', left: '1rem', top: '50%', transform: 'translateY(-50%)', color: 'var(--text-muted)' }} />
              <input
                id="register-email"
                className="form-input"
                style={{ paddingLeft: '2.75rem' }}
                name="email"
                type="email"
                placeholder="name@example.com"
                value={user.email}
                onChange={handleChange}
                required
              />
            </div>
          </div>
          <div className="form-group">
            <label>{t('password')}</label>
            <div style={{ position: 'relative' }}>
              <Lock size={18} style={{ position: 'absolute', left: '1rem', top: '50%', transform: 'translateY(-50%)', color: 'var(--text-muted)' }} />
              <input
                id="register-password"
                className="form-input"
                style={{ paddingLeft: '2.75rem' }}
                name="password"
                type="password"
                placeholder="••••••••"
                value={user.password}
                onChange={handleChange}
                required
              />
            </div>
          </div>
          <button className="auth-submit" type="submit" disabled={loading}>
            {loading ? t('creatingProfile') : t('registerNow')}
            {!loading && <UserPlus size={18} style={{ marginLeft: '0.5rem', verticalAlign: 'middle', display: 'inline' }} />}
          </button>
        </form>

        <p className="auth-alt">
          {t('alreadyHaveAccount')}? <Link to="/login">{t('signIn')}</Link>
        </p>
      </motion.div>
    </div>
  );
}

export default Register;
