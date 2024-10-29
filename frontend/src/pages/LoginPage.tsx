import React, {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import {Stack} from "@mui/material";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import {User} from "../types/types.ts"

export default function LoginPage() {
    const [isAuthenticated, setAuth] = useState(false);
    const [user, setUser] = useState<User>({ username: "", password: "", });
    const navigate = useNavigate()

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setUser({...user,
            [event.target.name] : event.target.value
        });
    }

    const handleLogin = () => {
        axios.post(`${import.meta.env.VITE_API_URL}/login`, user, {
            headers: { 'Content-Type': 'application/json' }
        }).then(res => {
            const jwtToken = res.headers.authorization;
            if (jwtToken !== null) {
                sessionStorage.setItem("jwt", jwtToken);
                setAuth(true);
            }
        }).catch(err => console.error(err));
    }

    if (isAuthenticated) {
        navigate("/");
    }

    return (
        <Box sx={{ ml: 0, mr: 0, textAlign: "center" }} >
            <Typography variant={"h3"} sx={{m: 10}}>
                Collaborative Code Editor
            </Typography>

            <Box className="login-form">
                <Stack spacing={2} alignItems="center" mt={2}>
                    <Typography variant="h4">Login</Typography>
                    <TextField
                        name="username"
                        label="Username"
                        onChange={handleChange} />
                    <TextField
                        type="password"
                        name="password"
                        label="Password"
                        onChange={handleChange}/>
                    <Button
                        variant="contained"
                        color="primary"
                        onClick={handleLogin}>
                        Login
                    </Button>
                </Stack>
            </Box>
        </Box>
    )
}
