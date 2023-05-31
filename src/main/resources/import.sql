-- Initial data for Producer table
INSERT INTO PRODUCER(ID, FIRST_NAME, LAST_NAME, CITY, ADDRESS, STATE) VALUES (1, 'Nile', 'Rogers', 'New-york', 'Azza', 'Israel');

-- Initial data for Singer table
INSERT INTO SINGER(ID, FIRST_NAME, LAST_NAME, CITY, ADDRESS, STATE) VALUES (1, 'Bob', 'Dylan', 'Migdal', 'Azza', 'Israel');
INSERT INTO SINGER(ID, FIRST_NAME, LAST_NAME) VALUES (2, 'Bob', 'Marley');
INSERT INTO SINGER(ID, FIRST_NAME, LAST_NAME) VALUES (3, 'Josh', 'Dylan');

-- Initial data for Song table
INSERT INTO SONG(ID, NAME, LENGTH, PRODUCER_ID) VALUES (1, 'the dylan song', 2.3, 1);
INSERT INTO SONG(ID, NAME, LENGTH) VALUES (2, 'Big brown eyes', 2.3);

-- Initial data for Singer_Song table
INSERT INTO SINGER_SONG(SINGER_ID, SONG_ID) VALUES (1, 1);
INSERT INTO SINGER_SONG(SINGER_ID, SONG_ID) VALUES (3, 1);
INSERT INTO SINGER_SONG(SINGER_ID, SONG_ID) VALUES (2, 2);