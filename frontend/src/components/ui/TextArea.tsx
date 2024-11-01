import {TextareaAutosize} from "@mui/material";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";

export default function TextArea({ title, readonly, text, setText }
                                     : { title: string; readonly: boolean, text: string, setText: any }) {
    return (
        <Box
            sx={{
                width: "100%",
                height: "100%"
            }}
        >

            <Typography
                variant="h6"
                component="div"
                sx={{
                    bgcolor: "primary.main",
                    fontSize: 16,
                    color: "white",
                    textTransform: "capitalize",
                    textAlign: "center",
                    padding: 1
                }}
            >
                {title}
            </Typography>

            <TextareaAutosize
                value={text}
                onChange={(e) => setText(e.target.value)}
                minRows={10}
                maxRows={10}
                style={{
                    fontSize: 14,
                    boxSizing: "border-box",
                    width: "100%",
                    resize: "none",
                    pointerEvents: (readonly ? "none" : "auto"),
                    margin: 0,
                    padding: 3
                }}
            />
        </Box>
    )
}