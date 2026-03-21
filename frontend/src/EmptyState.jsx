import React from "react";
import { motion } from "framer-motion";

function EmptyState({ icon: Icon, title, description, action }) {
  return (
    <motion.div 
      initial={{ opacity: 0, scale: 0.9 }}
      animate={{ opacity: 1, scale: 1 }}
      className="empty-state-refined"
    >
      <div className="empty-state-icon-wrapper">
        <Icon size={48} className="empty-state-icon" strokeWidth={1.5} />
      </div>
      <h3 className="empty-state-title">{title}</h3>
      <p className="empty-state-description">{description}</p>
      {action && (
        <div className="empty-state-action">
          {action}
        </div>
      )}
    </motion.div>
  );
}

export default EmptyState;
