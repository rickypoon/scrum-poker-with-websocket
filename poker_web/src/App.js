import 'bootstrap/dist/css/bootstrap.css';
import './App.css';
import { ListGroup, Button } from 'react-bootstrap';
import { useState, useEffect } from 'react';
import axios from 'axios';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import { Redirect } from 'react-router-dom';

function PointSelect({ ...props }) {
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

function App(props) {
  const [activeStoryId, setActiveStoryId] = useState('');
  const [backlog, setBacklog] = useState([]);
  const [currentSummary, setCurrentSummary] = useState({});
  const [summary, setSummary] = useState(new Map());
  const [estimatedStoryList, setEstimatedStoryList] = useState([]);
  const userEmail = props.history.location.state?.userEmail;

  const socket = new SockJS('http://localhost:8080/websocket');
  const stompClient = Stomp.over(socket);

  const onPointSelected = event => {
    let point = event.target.value;
    let storyId = event.target.id;
    stompClient.send('/app/estimate', {}, JSON.stringify({'storyId': storyId, 'userEmail': userEmail, 'point': point}));
  }

  const onStoryClicked = event => {
    let storyId = event.target.id;
    chooseStory(storyId, userEmail);
  }

  const chooseStory = (storyId, userEmail) => {
    stompClient.send('/app/user-all', {}, JSON.stringify({'storyId': storyId, 'userEmail': userEmail}));
  }

  const setActiveStory = activeStory => {
    console.log('body', activeStory.body);
    setActiveStoryId(JSON.parse(activeStory.body).storyId);
  }

  const displaySummary = estimationSummary => {
    let result = estimationSummary.body;
    if (result) {
      setCurrentSummary(JSON.parse(result));
      console.log(JSON.parse(estimationSummary.body));
    }
  }

  useEffect(() => {
    if (userEmail) {
      axios.post('http://localhost:8080/createUser', {
        userEmail: userEmail
      })
      .then((res) => {
        console.log(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
    }

    axios.get('http://localhost:8080/getAllStory')
      .then((res) => {
        console.log(res.data);
        setBacklog(res.data);
      })
      .catch((err) => {
        console.log(err);
      });

    stompClient.connect({}, () => {
      console.log('connected');
      stompClient.subscribe('/topic/user', setActiveStory);
      stompClient.subscribe('/topic/estimation_result', displaySummary);
    });
  }, []);

  useEffect(() => {
    console.log(activeStoryId);
  }, [activeStoryId]);

  useEffect(() => {
    if (!summary.has(currentSummary.storyId)) {
      setEstimatedStoryList([...estimatedStoryList, currentSummary.storyId])
      setActiveStoryId('');
    }
    setSummary(new Map(summary.set(currentSummary.storyId, currentSummary.summary)));
  }, [currentSummary]);

  return userEmail?(
    <div className="App">
      <div className="text-center mb-5">
        <h1>Scrum Poker</h1>
        <text>Hello {userEmail}!</text>
      </div>
      <ListGroup>
        {
          backlog.map(story => {
            let hasActive = (activeStoryId !== "");
            let isActive = activeStoryId === story.storyId;

            return (
              <ListGroup.Item active={isActive} key={story.storyId}>
                <div className="d-flex justify-content-between">
                  <h5 className="mb-1">{story.title}</h5>
                  <div>
                    {hasActive || <Button hidden={estimatedStoryList.includes(story.storyId)} className="m-1 btn-dark" id={story.storyId} onClick={onStoryClicked}>Set as active</Button>}
                    {isActive && <><label className="p-1">Estimation</label><PointSelect id={story.storyId} onChange={onPointSelected} disabled={!isActive} /></>}
                  </div>
                </div>
                <p className="mb-1">{summary.get(story.storyId)}</p>
              </ListGroup.Item>
            );
          })
        }
      </ListGroup>
    </div>
  )
  :
  <Redirect to="/login"></Redirect>;
}

export default App;
