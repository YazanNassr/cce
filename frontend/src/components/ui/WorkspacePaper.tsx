import {Paper} from "@mui/material";
import Typography from "@mui/material/Typography";
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import Box from "@mui/material/Box";
import IconButton from "@mui/material/IconButton";

export default function WorkspacePaper({ name } : {name: string}) {
    return (
        <Paper
            variant="outlined"
            sx={{
                display: "flex",
                justifyContent: "space-between",
                alignItems: "center",
                m: 1,
                px: 3,
                width: "90%",
                bgcolor: "primary.light",
            }}
        >
            <Typography
                component="a"
                sx={{
                    ml:2,
                    fontSize: "1.5rem",
                    fontFamily: "bold",
                    textDecoration: "none",
                    color: "primary.contrastText",
                }}
                href="/editor"
            >
                {name}
            </Typography>

            <Box>
                <IconButton>
                    <EditIcon
                        sx={{ m: 0.7, color: "primary.contrastText" }}
                    />
                </IconButton>
                <IconButton>
                    <DeleteIcon
                        sx={{ m: 0.7, color: "primary.contrastText" }}
                    />
                </IconButton>
            </Box>
        </Paper>
    );
}
