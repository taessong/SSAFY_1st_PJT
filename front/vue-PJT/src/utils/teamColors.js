// src/utils/teamColors.js
export const getTeamColorClass = (team, type = 'color') => {
  const colorClasses = {
    '맨시티': 'mancity',
    '맨유': 'manutd',
    '리버풀': 'liverpool',
    '첼시': 'chelsea',
    '아스널': 'arsenal',
    '토트넘': 'tottenham'
  };

  const baseClass = colorClasses[team] || '';
  
  return type === 'btn' ? `${baseClass}-btn` : `${baseClass}-color`;
};
