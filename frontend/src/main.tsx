import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.tsx'
import { ThemeProvider } from "@mui/material";
import defaultTheme from './themes/DefaultTheme'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <ThemeProvider theme={defaultTheme}>
          <App />
      </ThemeProvider>
  </StrictMode>,
)
