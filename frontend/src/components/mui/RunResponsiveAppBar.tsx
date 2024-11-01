import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import PlayArrowIcon from '@mui/icons-material/PlayArrow';
import SaveIcon from '@mui/icons-material/Save';
import LongMenu from "./FilesMenu.tsx";
import {useWorkspaceContext} from "../../hooks/WorkspaceContext.tsx";
import {runProject} from "../../services/api/projectApi.ts";
import {useStompClient} from "react-stomp-hooks";

export default function RunResponsiveAppBar() {
    const {workspace, setWorkspace} = useWorkspaceContext()
    const stompClient = useStompClient();


    const run = () => {
        const projectId = workspace.project.id??""
        const input = workspace.input
        const mainFilePath = workspace.activeFile.filePath

        runProject(projectId, input, mainFilePath)
            .then(out => setWorkspace({...workspace, output: out}))
    }

    const save = () => {
        const projectId = workspace.project.id??""
        if (stompClient) {
            stompClient.publish({
                destination: `/app/${projectId}/save`
            })
        }
    }

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar
                position="static"
            >
                <Toolbar
                    sx={{ bgcolor: "primary.dark" }}
                    variant="dense"
                >
                    <LongMenu />
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        {workspace.activeFile.parentPath}/{workspace.activeFile.fileName}
                    </Typography>

                    <IconButton
                        size="small"
                        edge="end"
                        aria-label="run"
                        sx={{ mx: 2, my: 0.5 }}
                        onClick={save}
                    >
                        <SaveIcon
                            sx={{color: "primary.contrastText"}}
                        />
                    </IconButton>

                    <IconButton
                        size="small"
                        edge="end"
                        aria-label="run"
                        sx={{ mx: 2, my: 0.5 }}
                        onClick={run}
                    >
                        <PlayArrowIcon
                            sx={{color: "primary.contrastText"}}
                        />
                    </IconButton>
                </Toolbar>
            </AppBar>
        </Box>
    );
}
