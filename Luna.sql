create table Moods (
	mood TEXT unique,
	explaination TEXT
);
Create TABLE context(
	con TEXT not null unique,
	explaination TEXT not null
);
Create table Res(
	res_out TEXT not null,
	input_in TEXT not null,
	context TEXT not null,
	mood TEXT,
	Foreign Key (context) REFERENCES context(con),
	Foreign Key (mood) REFERENCES Moods(mood)
);

Create TABLE rules(
	id SERIAL not null unique,
	rule TEXT not null
);
Create TABLE Words(
	id SERIAL not null unique,
	word TEXT not null,
	banned1 TEXT not null,
	banned2 TEXT not null,
	banned3 TEXT not null,
	banned4 TEXT not null,
	banned5 TEXT not null
);

Create TABLE Dates (
	SpecialDay TEXT,
	Explanation TEXT
);

create table users (
	name TEXT not null,
	love integer not null,
	hate integer not null
);
