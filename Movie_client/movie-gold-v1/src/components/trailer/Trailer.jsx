import React, { useRef } from 'react'
import './Trailer.css'
import { useParams } from 'react-router-dom';

const Trailer = () => {

    let params = useParams();
    const key = params.ytTrailerId;

    if (!key) {
      return <div style={{ color: 'white', padding: '20px' }}>No video ID found</div>;
    }

    //console.log('Video ID:', key);
    const videoUrl = `https://www.youtube.com/embed/${key}`;
    //console.log('Embed URL:', videoUrl);

  return (
    <div className='react-player-container'>
        <iframe
          width="100%"
          height="100%"
          src={videoUrl}
          title="Movie Trailer"
          frameBorder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
          allowFullScreen
        />
    </div>
  )
}

export default Trailer