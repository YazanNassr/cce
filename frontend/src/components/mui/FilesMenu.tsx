import * as React from 'react';
import IconButton from '@mui/material/IconButton';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import {useWorkspaceContext} from "../../hooks/WorkspaceContext.tsx";

const ITEM_HEIGHT = 48;

export default function LongMenu() {
    const {workspace, setWorkspace} = useWorkspaceContext()
    const options = workspace.project.files.map(file => `${file.parentPath}/${file.fileName}`)

    const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
    const open = Boolean(anchorEl);

    const handleClick = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorEl(event.currentTarget)
    };

    const handleChoose = (option: string) => {
        setWorkspace(workspace => {
            return {
            ...workspace,
            activeFile: workspace.project.files.find(file => `${file.parentPath}/${file.fileName}` === option) || workspace.activeFile
        }})
    };


    const handleClose = () => {
        setAnchorEl(null)
    };

    return (
        <div>
            <IconButton
                aria-label="more"
                id="long-button"
                aria-controls={open ? 'long-menu' : undefined}
                aria-expanded={open ? 'true' : undefined}
                aria-haspopup="true"
                onClick={handleClick}
                sx={{color: "primary.contrastText"}}
            >
                <MoreVertIcon />
            </IconButton>
            <Menu
                id="long-menu"
                MenuListProps={{
                    'aria-labelledby': 'long-button',
                }}
                anchorEl={anchorEl}
                open={open}
                onClose={handleClose}
                slotProps={{
                    paper: {
                        style: {
                            maxHeight: ITEM_HEIGHT * 4.5,
                            width: '20ch',
                        },
                    },
                }}
            >
                {options.map((option) => (
                    <MenuItem key={option} onClick={() => handleChoose(option)}>
                        {option}
                    </MenuItem>
                ))}
            </Menu>
        </div>
    );
}
