import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";

import "../assets/loginPageStyles.css"

export default function LoginPage() {

    return (
        <Box sx={{ml: 0, mr: 0, textAlign: "center"}}>
            <Typography variant={"h3"} sx={{m: 10}}>
                Collaborative Code Editor
            </Typography>
            <Box className="login-form">
                <form>
                    <Typography variant="h2">Login</Typography>
                    <div className="content">
                        <div className="input-field">
                            <input type="text" placeholder="Username" autoComplete="nope"/>
                        </div>
                        <div className="input-field">
                            <input type="password" placeholder="Password" autoComplete="new-password"/>
                        </div>
                    </div>

                    <div className="action">
                        <button>Register</button>
                        <button>Sign in</button>
                    </div>
                </form>
            </Box>
        </Box>
    )

}
