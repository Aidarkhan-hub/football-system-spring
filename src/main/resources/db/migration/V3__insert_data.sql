INSERT INTO teams(name, city, coach_name) VALUES
('Narxoz FC', 'Almaty', 'A. Coach'),
('Astana United', 'Astana', 'B. Coach'),
('Shymkent Stars', 'Shymkent', 'C. Coach')
ON CONFLICT (name) DO NOTHING;

INSERT INTO players(full_name, position, age, jersey_number, goals, team_id) VALUES
('Aidarhan Doyanbayev', 'MID', 21, 8, 2, (SELECT id FROM teams WHERE name = 'Narxoz FC')),
('Ali Khan', 'FWD', 23, 9, 5, (SELECT id FROM teams WHERE name = 'Narxoz FC')),
('Daniyar S.', 'DEF', 25, 4, 0, (SELECT id FROM teams WHERE name = 'Astana United'))
ON CONFLICT DO NOTHING;

INSERT INTO matches(home_team_id, away_team_id, home_goals, away_goals, match_time, stadium) VALUES
((SELECT id FROM teams WHERE name='Narxoz FC'), (SELECT id FROM teams WHERE name='Astana United'), 2, 1, NOW() - INTERVAL '7 days', 'Narxoz Stadium'),
((SELECT id FROM teams WHERE name='Astana United'), (SELECT id FROM teams WHERE name='Shymkent Stars'), 0, 0, NOW() - INTERVAL '3 days', 'Astana Arena')
ON CONFLICT DO NOTHING;
