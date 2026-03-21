import React, { useState, useEffect } from "react";
import { profileService } from "./api";
import { useTranslation } from "react-i18next";

const ProfilePage = () => {
  const { t } = useTranslation();
  const [profile, setProfile] = useState({
    name: "",
    age: "",
    gender: "MALE",
    address: "",
    mobileNumber: "",
    state: "",
    income: "",
    caste: "GENERAL",
    occupation: "",
  });
  const [loading, setLoading] = useState(true);
  const [saving, setSaving] = useState(false);
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetchProfile();
  }, []);

  const fetchProfile = async () => {
    try {
      const response = await profileService.getProfile();
      if (response.data) {
        setProfile(response.data);
      }
    } catch (error) {
      console.error("Error fetching profile:", error);
    } finally {
      setLoading(false);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProfile((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSaving(true);
    setMessage("");
    try {
      await profileService.updateProfile(profile);
      setMessage(t('profileUpdated'));
      setTimeout(() => setMessage(""), 3000);
    } catch (error) {
      setMessage("Error updating profile: " + (error.message || "Unknown error"));
    } finally {
      setSaving(false);
    }
  };

  if (loading) {
    return (
      <div className="profile-container flex justify-center py-20">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-primary"></div>
      </div>
    );
  }

  const states = [
    "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana", 
    "Himachal Pradesh", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", 
    "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", 
    "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal", "Delhi"
  ];

  return (
    <div className="profile-container animate-fade-in">
      <div className="profile-card">
        <div className="profile-header">
          <h1 className="profile-title">{t('profile')}</h1>
          <p className="profile-subtitle">Update your information for personalized scheme recommendations.</p>
        </div>

        <form onSubmit={handleSubmit} className="profile-form">
          {message && (
            <div className={`p-4 mb-6 rounded-lg text-sm ${message.includes("Error") ? "bg-red-100 text-red-700" : "bg-green-100 text-green-700"} transition-all duration-300`}>
              {message}
            </div>
          )}

          <div className="profile-grid">
            {/* Personal Details */}
            <div className="form-section">
              <h2>{t('personalDetails')}</h2>
              <div className="form-group">
                <label>{t('name')}</label>
                <input
                  type="text"
                  name="name"
                  value={profile.name || ""}
                  onChange={handleChange}
                  required
                />
              </div>
              <div className="form-group">
                <label>{t('mobileNumber')}</label>
                <input
                  type="text"
                  name="mobileNumber"
                  value={profile.mobileNumber || ""}
                  onChange={handleChange}
                  placeholder="10 digit mobile number"
                />
              </div>
              <div className="form-group">
                <label>{t('address')}</label>
                <textarea
                  name="address"
                  value={profile.address || ""}
                  onChange={handleChange}
                  rows="3"
                />
              </div>
            </div>

            {/* Demographic Info */}
            <div className="form-section">
              <h2>{t('demographicInfo')}</h2>
              <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '1rem' }}>
                <div className="form-group">
                  <label>{t('age')}</label>
                  <input
                    type="number"
                    name="age"
                    value={profile.age || ""}
                    onChange={handleChange}
                  />
                </div>
                <div className="form-group">
                  <label>{t('gender')}</label>
                  <select
                    name="gender"
                    value={profile.gender || "MALE"}
                    onChange={handleChange}
                  >
                    <option value="MALE">Male</option>
                    <option value="FEMALE">Female</option>
                  </select>
                </div>
              </div>
              <div className="form-group">
                <label>{t('state')}</label>
                <select
                  name="state"
                  value={profile.state || ""}
                  onChange={handleChange}
                >
                  <option value="">Select State</option>
                  {states.map(s => <option key={s} value={s}>{s}</option>)}
                </select>
              </div>
              <div className="form-group">
                <label>{t('caste')}</label>
                <select
                  name="caste"
                  value={profile.caste || "GENERAL"}
                  onChange={handleChange}
                >
                  <option value="GENERAL">General</option>
                  <option value="OBC">OBC</option>
                  <option value="SC">SC</option>
                  <option value="ST">ST</option>
                  <option value="EWS">EWS</option>
                </select>
              </div>
            </div>
          </div>

          {/* Financial Status */}
          <div className="form-section" style={{ marginTop: '2.5rem', paddingTop: '2.5rem', borderTop: '2px solid var(--primary-bg)' }}>
            <h2>{t('financialStatus')}</h2>
            <div className="profile-grid">
              <div className="form-group">
                <label>{t('income')} (Annual)</label>
                <input
                  type="number"
                  name="income"
                  value={profile.income || ""}
                  onChange={handleChange}
                  placeholder="e.g. 200000"
                />
              </div>
              <div className="form-group">
                <label>{t('occupation')}</label>
                <input
                  type="text"
                  name="occupation"
                  value={profile.occupation || ""}
                  onChange={handleChange}
                  placeholder="e.g. Farmer, Student"
                />
              </div>
            </div>
          </div>

          <div style={{ marginTop: '3rem' }}>
            {message && (
              <div className={`p-4 mb-4 rounded-lg text-sm ${message.includes("Error") ? "bg-red-100 text-red-700" : "bg-green-100 text-green-700"} animate-fade-in`}>
                {message}
              </div>
            )}
            <button
              type="submit"
              disabled={saving}
              className="btn-save"
            >
              {saving ? (
                <>
                  <div className="animate-spin rounded-full h-4 w-4 border-b-2 border-white"></div>
                  Saving...
                </>
              ) : t('saveProfile')}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default ProfilePage;
