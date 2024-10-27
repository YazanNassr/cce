import {Container, CssBaseline} from '@mui/material'

import {BrowserRouter, Route, Routes} from "react-router-dom";
import MainResponsiveAppBar from "./components/mui/MainResponsiveAppBar.tsx";
import HomePage from "./pages/HomePage.tsx";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import EditorPageDataFetcher from "./pages/EditorPageDataFetcher.tsx";
import { StompSessionProvider } from "react-stomp-hooks";
import Tmp from './stomp.tsx';
import LoginPage from "./pages/LoginPage.tsx";

const queryClient = new QueryClient();

function App() {

  return (
    <StompSessionProvider url={"http://localhost:8080/stomp"} >
      <QueryClientProvider client={queryClient}>
          <Container>
              <CssBaseline />
              <BrowserRouter>
                  <Routes>
                      <Route path="/" element={<HomePage />} />
                      <Route path="/home" element={<HomePage />} />

                      <Route path="/editor" element={<EditorPageDataFetcher />} />
                      <Route path="/login" element={<LoginPage />} />
                      <Route path="/websockets" element={<Tmp />} />
                  </Routes>
              </BrowserRouter>
          </Container>
      </QueryClientProvider>
    </StompSessionProvider>
  );
}

export default App
