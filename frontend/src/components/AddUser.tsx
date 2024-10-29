import {User} from '../types/types.ts'
import {Dialog, DialogActions, DialogContent, DialogTitle, Stack, TextField} from "@mui/material";

import React, {useState} from "react";
import {useMutation, useQueryClient} from "@tanstack/react-query";
import {createUser} from "../services/api/userApi.ts"
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import Snackbar from "@mui/material/Snackbar";

export default function AddUser() {
    const queryClient = useQueryClient();

    const [openSnackbar, setOpenSnackbar] = useState<boolean>(false);
    const [open, setOpen] = React.useState(false);
    const [user, setUser] = React.useState<User>({
        name: "",
        role: ""
    });

    const { mutate } = useMutation({
        mutationFn: createUser,
        onSuccess: () => {
            setOpenSnackbar(true);
            queryClient.invalidateQueries({queryKey: ["users"]})
                .then()
        },
        onError: err => console.log(err)
    })

    const handleClickOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);
    const handleChange =
        (event: React.ChangeEvent<HTMLInputElement>) => {
            setUser({...user, [event.target.name]: event.target.value})}

    const handleSave = () => {
        mutate(user)
        setUser({ "name": "", "role": "" })
        handleClose()
    }

    return (
        <Box>
            <Button
                onClick={handleClickOpen}
            >
                Create New User
            </Button>

            <Dialog
                open={open}
                onClose={handleClose}
            >
                <DialogTitle>New User</DialogTitle>
                <DialogContent>
                    <Stack spacing={2}>

                        <TextField
                            name="name"
                            placeholder="User Name"
                            variant="outlined"
                            onChange={handleChange}
                        />

                        <TextField
                            name="role"
                            placeholder="User Role"
                            variant="outlined"
                            onChange={handleChange}
                        />
                    </Stack>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose} variant="outlined">Cancel</Button>
                    <Button onClick={handleSave} variant="outlined">Save</Button>
                </DialogActions>
            </Dialog>

            <Snackbar
                open={openSnackbar}
                autoHideDuration={2000}
                onClose={() => setOpenSnackbar(false)}
                message={"User Added Successfully"}
            />
        </Box>
    )
}