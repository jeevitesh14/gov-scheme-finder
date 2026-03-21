import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import LanguageDetector from 'i18next-browser-languagedetector';
import { translations } from './translations';

const resources = {
  en: {
    translation: translations.English
  },
  hi: {
    translation: translations.Hindi
  },
  te: {
    translation: translations.Telugu
  }
};

i18n
  .use(LanguageDetector)
  .use(initReactI18next)
  .init({
    resources,
    fallbackLng: 'en',
    detection: {
      order: ['localStorage', 'navigator'],
      caches: ['localStorage'],
      lookupLocalStorage: 'language', // Match existing localStorage key
    },
    interpolation: {
      escapeValue: false,
    },
  });

export default i18n;
