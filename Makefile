run_all_in_parallel:
	make clean_it test_parallel

clean_it:
	mvn clean

test_parallel:
	make -j test_ipad_air test_ipad_air_2

test_ipad_air_2:
	appiumVersion=1.6 deviceName="iPad Air 2" deviceOrientation=portrait platformVersion=11.4.1  platformName=iOS mvn clean install

test_ipad_air:
	appiumVersion=1.6 deviceName="iPad Air" deviceOrientation=landscape platformVersion=10.1.1 platformName=iOS mvn clean install
