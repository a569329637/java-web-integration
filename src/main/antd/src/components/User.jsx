import React, { PropTypes } from 'react';

const User = ({ user }) => (
  <div>
    <h2>{user.name}</h2>
    <img
      src={user.avatar_url}
      alt={user.name}
      style={{ width: 100, height: 100 }}
    />
  </div>
);

User.propTypes = {
  user: PropTypes.shape({
    name: PropTypes.string.isRequired,
    avatar_url: PropTypes.string.isRequired,
  }).isRequired,
};

export default User;
