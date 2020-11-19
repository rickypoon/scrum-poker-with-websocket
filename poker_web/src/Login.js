import { useHistory } from 'react-router-dom';
import {useEffect, useState} from 'react';
import { Form, Col, Row, Button, Alert } from 'react-bootstrap';
import './Login.css';

export default function Login() {
    const history = useHistory();
    const [userEmail, setUserEmail] = useState('');
    const [isAlert, setAlert] = useState(false);

    const onSubmit = (event) => {
        event.preventDefault();
        if (userEmail) {
            let path = `/poker`;
            history.push(path, { userEmail: userEmail});
        }
    }

    useEffect(() => {
        setAlert(userEmail == '');
    }, [userEmail]);

    return (
        <div className="Login">
            <Form>
                <Form.Group as={Row} controlId="formHorizontalEmail">
                    <Form.Label column sm={2}>
                        Email
                    </Form.Label>
                    <Col sm={10}>
                        <Form.Control value={userEmail} onChange={(e) => setUserEmail(e.target.value)} type="email" placeholder="Email" />
                    </Col>
                </Form.Group>

                <Form.Group as={Row}>
                    <Col sm={{ span: 10, offset: 2 }}>
                        <Button type="submit" onClick={onSubmit}>Login</Button>
                    </Col>
                </Form.Group>

                <Alert variant="warning" hidden={!isAlert}>
                    Please input user email
                </Alert>
            </Form>
        </div>
    );
}