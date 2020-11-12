import 'bootstrap/dist/css/bootstrap.css';
import './App.css';
import { ListGroup, Button } from 'react-bootstrap';
import { useState } from 'react';

function PointSelectOptions() {
  return (
    <>
      <option hidden disabled selected value></option>
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>5</option>
      <option>8</option>
      <option>13</option>
      <option>21</option>
      <option>no_idea</option>
      <option>resign</option>
    </>
  );
}

function App() {
  const [activeStoryId, setActiveStoryId] = useState(0);
  const [point, setPoint] = useState('');

  const onPointSelected = event => {
    alert(event.target.value);
  }

  const onStoryClicked = event => {
    setActiveStoryId(event.target.id);
  }

  return (
    <div className="App">
      <h1 class="text-center">Sprint backlog</h1>
      <ListGroup>
        <ListGroup.Item active={activeStoryId == 1}>
          <div class="d-flex justify-content-between">
            <h5 class="mb-1">implement chat feature</h5>
            <div>
              <Button className="m-1 btn-dark" id="1" onClick={onStoryClicked}>Make active</Button>
              <select onChange={onPointSelected} disabled={activeStoryId != 1}>
                <PointSelectOptions />
              </select>
            </div>
          </div>
          <p class="mb-1">A gave 2 points, B gave 2 points, B gave 3 points</p>
        </ListGroup.Item>
        <ListGroup.Item active={activeStoryId == 2}>
          <div class="d-flex justify-content-between">
            <h5 class="mb-1">add social login</h5>
            <div>
              <Button className="m-1 btn-dark" id="2" onClick={onStoryClicked}>Make active</Button>
              <select onChange={onPointSelected} disabled={activeStoryId != 2}>
                <PointSelectOptions />
              </select>
            </div>
          </div>
          <p class="mb-1">A gave 2 points, B gave 2 points, B gave 3 points</p>
        </ListGroup.Item>
        <ListGroup.Item active={activeStoryId == 3}>
          <div class="d-flex justify-content-between">
            <h5 class="mb-1">implement profile pic upload</h5>
            <div>
              <Button className="m-1 btn-dark" id="3" onClick={onStoryClicked}>Make active</Button>
              <select onChange={onPointSelected} disabled={activeStoryId != 3}>
                <PointSelectOptions />
              </select>
            </div>
          </div>
          <p class="mb-1">A gave 2 points, B gave 2 points, B gave 3 points</p>
        </ListGroup.Item>
      </ListGroup>
    </div>
  );
}

export default App;
