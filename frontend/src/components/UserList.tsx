import {User} from "../types.ts";
import {useMutation, useQuery, useQueryClient} from "@tanstack/react-query";
import Typography from "@mui/material/Typography";
import { getUsers, deleteUser } from "../services/api/userApi.ts"
import {DataGrid, GridCellParams, GridColDef} from "@mui/x-data-grid";
import IconButton from "@mui/material/IconButton";
import DeleteIcon from '@mui/icons-material/Delete';
import Snackbar from '@mui/material/Snackbar';
import {useState} from "react";
import AddUser from "./AddUser";
import Box from "@mui/material/Box";


export default function UserList() {
    const [openSnackbar, setOpenSnackbar] = useState<boolean>(false);

    const queryClient = useQueryClient();

    const { data, error, isSuccess } = useQuery<User[], Error>({
        queryKey: ['users'],
        queryFn: getUsers
    });

    const { mutate } = useMutation({
        mutationFn: deleteUser,
        onSuccess: () => {
            setOpenSnackbar(true);
            queryClient.invalidateQueries({queryKey: ['users']})
                .then();
        },
        onError: err => console.log(err)
    });

    const columns: GridColDef[] = [
        {field: 'id', headerName: 'User Id', flex: 1},
        {field: 'name', headerName: 'User Name', flex: 1},
        {field: 'role', headerName: 'Role', flex: 1},
        {
            field: 'delete',
            headerName: '',
            flex: 1,
            maxWidth: 90,
            align: "center",
            sortable: false,
            filterable: false,
            disableColumnMenu: true,
            renderCell: (params: GridCellParams) =>
                <IconButton
                    children={<DeleteIcon />}
                    onClick={() => {
                        if (window.confirm(`Are you sure you want to delete ${params.row.role} ${params.row.name}?`)) {
                            mutate(params.row);
                        }
                    }}
                />
        },
    ]

    if (!isSuccess) {
        return <Typography variant="body2">Loading...</Typography>
    } else if (error) {
        return <Typography variant="body2">Error when fetching users...</Typography>
    } else {
        return (
            <Box
                sx={{my: 1}}
            >
                <AddUser />
                <DataGrid
                    rows={data}
                    columns={columns}
                    getRowId={row => row.id}
                    disableRowSelectionOnClick={true}
                />

                <Snackbar
                    open={openSnackbar}
                    autoHideDuration={2000}
                    onClose={() => setOpenSnackbar(false)}
                    message={"User Deleted Successfully"}
                />
            </Box>
        )
    }
}