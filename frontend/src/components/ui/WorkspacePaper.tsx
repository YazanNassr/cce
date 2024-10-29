import {Paper} from "@mui/material";
import Typography from "@mui/material/Typography";
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import Box from "@mui/material/Box";
import IconButton from "@mui/material/IconButton";
import {deleteProject} from "../../services/api/projectApi.ts";

export default function WorkspacePaper({ projectId, name } : {projectId: string, name: string}) {
    const deleteWorkspace = () => {
        deleteProject(projectId)
            .then(() => {window.location.reload()})
            .catch((error: Error) => console.log(error));
    }
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
                href={"/editor/" + projectId}
            >
                {name}
            </Typography>

            <Box>
                <IconButton>
                    <EditIcon
                        sx={{ m: 0.7, color: "primary.contrastText" }}
                    />
                </IconButton>
                <IconButton
                    onClick={deleteWorkspace}
                >
                    <DeleteIcon
                        sx={{ m: 0.7, color: "primary.contrastText" }}
                    />
                </IconButton>
            </Box>
        </Paper>
    );
}
