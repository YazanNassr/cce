import {createTheme} from "@mui/material";

const defaultTheme = createTheme({
    palette: {
        primary: {
            main: "#0077b6",
            light: "#00a2c3",
            dark: "#03045e",
            contrastText: "#ffffff",
        },
        secondary: {
            main: "#243642",
            light: "#387478",
            dark: "#2b2d42",
            contrastText: "#ffffff",
        },
        error: {
            main: "#c1121f",
        },
        warning: {
            main: "#fb8500",
        },
        info: {
            main: "#0077b6",
        },
        success: {
            main: "#6a994e",
        },
        background: {
            default: "#fbfcf8",
        }
    }
});

export default defaultTheme;

