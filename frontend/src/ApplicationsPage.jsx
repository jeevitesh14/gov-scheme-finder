import React, { useEffect, useState } from "react";
import { applicationService } from "./api";
import SkeletonCard from "./SkeletonCard";
import EmptyState from "./EmptyState";
import { motion } from "framer-motion";
import { useTranslation } from "react-i18next";
import { CheckCircle, Clock, XCircle, Search, Calendar, Tag, Inbox } from "lucide-react";

const statusConfig = {
  APPLIED: { color: "#3b82f6", icon: Clock, label: "Applied" },
  UNDER_REVIEW: { color: "#f59e0b", icon: Search, label: "Under Review" },
  APPROVED: { color: "#10b981", icon: CheckCircle, label: "Approved" },
  REJECTED: { color: "#ef4444", icon: XCircle, label: "Rejected" },
};

function ApplicationsPage() {
  const { t } = useTranslation();
  const [applications, setApplications] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchApplications();
  }, []);

  const fetchApplications = async () => {
    try {
      const response = await applicationService.getApplications();
      setApplications(response.data || []);
    } catch (err) {
      console.error("Failed to fetch applications:", err);
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return (
      <div className="profile-container">
        <div className="profile-card skeleton-card">
          <div className="profile-header skeleton">
            <div className="skeleton-title" style={{ width: '30%' }}></div>
            <div className="skeleton-text" style={{ width: '50%' }}></div>
          </div>
          <div className="profile-form">
            <div className="schemes-grid">
              {[...Array(3)].map((_, i) => <SkeletonCard key={i} />)}
            </div>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="profile-container animate-fade-in">
      <div className="profile-card">
        <div className="profile-header">
          <h1 className="profile-title">{t('applications')}</h1>
          <p className="profile-subtitle">Track the status of your submitted scheme applications</p>
        </div>

        <div className="profile-form">

      {applications.length === 0 ? (
        <EmptyState 
          icon={Inbox}
          title="No applications found"
          description="Go to the dashboard to find and apply for schemes."
        />
      ) : (
        <div className="applications-list" style={{ display: "grid", gap: "1rem", marginTop: "2rem" }}>
          {applications.map((app) => {
            const status = statusConfig[app.status] || statusConfig.APPLIED;
            const StatusIcon = status.icon;

            return (
              <motion.div
                key={app.id}
                initial={{ opacity: 0, y: 10 }}
                animate={{ opacity: 1, y: 0 }}
                className="application-item"
                style={{
                  background: "var(--bg)",
                  borderRadius: "16px",
                  padding: "1.5rem",
                  display: "flex",
                  justifyContent: "space-between",
                  alignItems: "center",
                  border: "1px solid var(--border)",
                  marginBottom: "1rem"
                }}
              >
                <div>
                  <h3 style={{ margin: 0, fontSize: "1.2rem", color: "white" }}>{app.schemeName}</h3>
                  <div style={{ display: "flex", gap: "1rem", marginTop: "0.5rem", color: "rgba(255,255,255,0.6)", fontSize: "0.9rem" }}>
                    <span style={{ display: "flex", alignItems: "center", gap: "0.3rem" }}>
                      <Calendar size={14} />
                      {new Date(app.applicationDate).toLocaleDateString()}
                    </span>
                    <span style={{ display: "flex", alignItems: "center", gap: "0.3rem" }}>
                      <Tag size={14} />
                      ID: #{app.id}
                    </span>
                  </div>
                </div>

                <div
                  style={{
                    display: "flex",
                    alignItems: "center",
                    gap: "0.5rem",
                    padding: "0.5rem 1rem",
                    borderRadius: "20px",
                    backgroundColor: `${status.color}20`,
                    color: status.color,
                    border: `1px solid ${status.color}40`,
                    fontWeight: "600",
                    fontSize: "0.9rem",
                  }}
                >
                  <StatusIcon size={16} />
                  {status.label}
                </div>
              </motion.div>
            );
          })}
        </div>
      )}
        </div>
      </div>
    </div>
  );
}

export default ApplicationsPage;
