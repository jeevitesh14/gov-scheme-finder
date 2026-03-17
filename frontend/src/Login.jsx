import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { motion } from "framer-motion";
import { ShieldCheck, LogIn, Mail, Lock, AlertCircle, ArrowRight } from "lucide-react";
import { authService } from "./api";

function Login({ onLogin }) {
  const [user, setUser] = useState({ email: "", password: "" });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => setUser({ ...user, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);
    try {
      const response = await authService.login(user);
      const data = response.data;
      onLogin({ email: data.email, role: data.role, token: data.token, name: data.name });
      if (data.role === "ADMIN") {
        navigate("/admin");
      } else {
        navigate("/");
      }
    } catch (err) {
      setError(err.message || "Invalid credentials or server error.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-page">
      <motion.div 
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        className="auth-card"
      >
        <div className="auth-logo">
          <ShieldCheck size={48} className="text-primary" style={{ margin: '0 auto 1.5rem' }} />
          <h1>GovScheme Finder</h1>
          <p>Access government benefits with ease.</p>
        </div>

        <h2 style={{ textAlign: 'center', marginBottom: '1.5rem', fontSize: '1.5rem', fontWeight: 800 }}>Welcome Back</h2>

        {error && (
          <div className="auth-error">
            <AlertCircle size={16} />
            {error}
          </div>
        )}

        <form onSubmit={handleSubmit} className="auth-form">
          <div className="form-group">
            <label>Email Address</label>
            <div style={{ position: 'relative' }}>
              <Mail size={18} style={{ position: 'absolute', left: '1rem', top: '50%', transform: 'translateY(-50%)', color: 'var(--text-muted)' }} />
              <input
                id="login-email"
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
            <label>Password</label>
            <div style={{ position: 'relative' }}>
              <Lock size={18} style={{ position: 'absolute', left: '1rem', top: '50%', transform: 'translateY(-50%)', color: 'var(--text-muted)' }} />
              <input
                id="login-password"
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
            {loading ? "Verifying..." : "Sign In to Dashboard"}
            {!loading && <ArrowRight size={18} style={{ marginLeft: '0.5rem', verticalAlign: 'middle', display: 'inline' }} />}
          </button>
        </form>

        <p className="auth-alt">
          New here? <Link to="/register">Create an account</Link>
        </p>
      </motion.div>
    </div>
  );
}

export default Login;
