import { Container, CssBaseline             } from '@mui/material'
import { BrowserRouter, Route, Routes       } from "react-router-dom";
import { QueryClient, QueryClientProvider   } from "@tanstack/react-query";
import { StompSessionProvider               } from "react-stomp-hooks";
import { ThemeProvider                      } from "@mui/material";
import   defaultTheme                         from './themes/DefaultTheme'

import   EditorPageDataFetcher                from "./pages/EditorPageDataFetcher.tsx";
import   LoginPage                            from "./pages/LoginPage.tsx";
import   HomePage                             from "./pages/HomePage.tsx";
import PageNotFound from "./pages/PageNotFound.tsx";

const queryClient = new QueryClient();

export default function App() {
    return (
        <StompSessionProvider url={"ws://localhost:8080/stomp"}>
            <QueryClientProvider client={queryClient}>
                <ThemeProvider theme={defaultTheme}>
                    <Container>
                        <CssBaseline />
                        <BrowserRouter>
                            <Routes>
                                <Route path="/login" element={<LoginPage />} />
                                <Route path="/" element={<HomePage />} />
                                <Route path="/home" element={<HomePage />} />
                                <Route path="/editor/:projectId" element={<EditorPageDataFetcher />} />
                                <Route path="*" element={<PageNotFound />} />
                            </Routes>
                        </BrowserRouter>
                    </Container>
                </ThemeProvider>
            </QueryClientProvider>
        </StompSessionProvider>
    );
}
