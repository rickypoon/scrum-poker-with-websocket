import 'bootstrap/dist/css/bootstrap.css';
import './App.css';
import { ListGroup, Button } from 'react-bootstrap';
import { useState, useEffect } from 'react';
import axios from 'axios';

function PointSelect({...props}) {
  return (
    <select {...props}>
      <option hidden></option>
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>5</option>
      <option>8</option>
      <option>13</option>
      <option>21</option>
      <option>no_idea</option>
      <option>resign</option>
    </select>
  );
}

function App() {
  const [activeStoryId, setActiveStoryId] = useState(0);
  const [userEmail, setUserEmail] = useState('peter@gmail.com');
  const [backlog, setBacklog] = useState([]);

  const onPointSelected = event => {
    let point = event.target.value;

    axios.post('http://localhost:8080/estimate', {
      storyId: activeStoryId,
      userEmail: userEmail,
      point: point
    })
    .then(() => {
      console.log("Estimation submitted:", point);
    })
    .catch((err) => {
      console.log(err);
    });
  }

  const onStoryClicked = event => {
    setActiveStoryId(event.target.id);
  }

  const onNameChange = event => {
    setUserEmail(event.target.value);
  }

  useEffect(() => {
    axios.get('http://localhost:8080/getAllStory')
    .then((res) => {
      console.log(res.data);
      setBacklog(res.data);
    })
    .catch((err) => {
      console.log(err);
    });
  }, []);

  return (
    <div className="App">
      <h1 className="text-center">Sprint backlog</h1>
      <input type="text" onChange={onNameChange} className="form-control mb-1" placeholder="User email"></input>
      <ListGroup>
        {
          backlog.map(story => {
            let isActive = activeStoryId === story.storyId;
            return (
              <ListGroup.Item active={isActive} key={story.storyId}>
                <div className="d-flex justify-content-between">
                  <h5 className="mb-1">{story.title}</h5>
                  <div>
                    <Button className="m-1 btn-dark" id={story.storyId} onClick={onStoryClicked}>Make active</Button>
                      <PointSelect onChange={onPointSelected} disabled={!isActive} />
                  </div>
                </div>
                <p className="mb-1">{story.description}</p>
              </ListGroup.Item>
            );
          })
        }
      </ListGroup>
    </div>
  );
}

export default App;
