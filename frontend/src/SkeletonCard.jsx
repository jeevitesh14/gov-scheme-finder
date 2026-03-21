import React from "react";

function SkeletonCard() {
  return (
    <div className="scheme-card skeleton-card">
      <div className="card-top">
        <div className="card-icon-wrapper skeleton"></div>
      </div>
      <div className="card-content">
        <div className="skeleton skeleton-title"></div>
        <div className="skeleton skeleton-text"></div>
        <div className="skeleton skeleton-text" style={{ width: '80%' }}></div>
      </div>
      <div className="card-badges">
        <div className="skeleton skeleton-badge"></div>
        <div className="skeleton skeleton-badge"></div>
      </div>
      <div className="card-footer" style={{ marginTop: 'auto', paddingTop: '1rem' }}>
        <div className="skeleton skeleton-button"></div>
      </div>
    </div>
  );
}

export default SkeletonCard;
