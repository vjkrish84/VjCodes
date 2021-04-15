export default function (state, action) {
    const { type, payload } = action;
    switch(type)
    {
    case 'FETCH_SUCCESS':
        {
        console.log("inside reducer");
            return state;
        }
    default:
        return null;
    }
};