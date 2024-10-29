import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import {useState} from "react";
import {createProject} from "../../services/api/projectApi.ts";

export default function FormDialog({ open, handleClose } : {open: boolean, handleClose: () => void}) {
    const [projectName, setProjectName] = useState<string>();

    return (
            <Dialog
                open={open}
                onClose={handleClose}
                PaperProps={{
                    component: 'form',
                    onSubmit: (event: React.FormEvent<HTMLFormElement>) => {
                        event.preventDefault();

                        createProject({name: projectName ?? "untitled", files: []})
                            .catch(err => console.log(err))

                        handleClose();
                    },
                }}
            >
                <DialogTitle>New Workspace</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        Enter your new workspace name
                    </DialogContentText>
                    <TextField
                        autoFocus
                        required
                        margin="dense"
                        id="name"
                        name="name"
                        label="Workspace Name"
                        type="text"
                        fullWidth
                        variant="standard"
                        value={projectName}
                        onChange={(e) => setProjectName(e.target.value)}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button type="submit">Submit</Button>
                </DialogActions>
            </Dialog>
    );
}
