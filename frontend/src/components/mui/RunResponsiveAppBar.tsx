import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import PlayArrowIcon from '@mui/icons-material/PlayArrow';
import LongMenu from "./FilesMenu.tsx";
import {useWorkspaceContext} from "../../WorkspaceContext.tsx";

export default function RunResponsiveAppBar() {
    const {workspace} = useWorkspaceContext()

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
